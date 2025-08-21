package com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository,
    private val foodRepository: FoodRepository,
    private val dataStoreRepository: DataStoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private suspend fun requireUserId(): Int {
        return dataStoreRepository.getUserIdFlow().first()
            ?: throw IllegalStateException("User id is null. Make sure to save it in DataStore.")
    }

    private val _createDietState = mutableStateOf<Boolean?>(null)
    val createDietState: State<Boolean?> get() = _createDietState

    private val _dietId = mutableStateOf<Int?>(null)
    val dietId: State<Int?> get() = _dietId

    private val _dietName = mutableStateOf<String?>("")
    val dietName: State<String?> get() = _dietName

    fun createDiet() {
        val foods = selectedFoods.map {
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }

        viewModelScope.launch {
            runCatching {
                val uid = requireUserId()
                val dietRequest = DietRequestDto(
                    userId = uid,
                    name = dietName.value ?: "",
                    foods = foods
                )
                dietRepository.createDiet(request = dietRequest)
            }.onSuccess {
                _createDietState.value = true
                Log.d("createDiet", "success")
            }.onFailure {
                _createDietState.value = false
                Log.e("createDiet", it.message ?: "Unknown error")
            }
        }
    }

    private val _selectedFoods = mutableStateOf<List<FoodWithQuantity>?>(null)
    val selectedFoods: List<FoodWithQuantity> get() = _selectedFoods.value ?: emptyList()

    init {
        val dietId = savedStateHandle.get<Int>("dietId") ?: -1
        val fwqRawString = savedStateHandle.get<String>("fwqRaw") ?: ""
        val name = savedStateHandle.get<String>("name") ?: ""

        if (dietId != -1 && fwqRawString.isNotEmpty()) {
            val parsed = fwqRawString.split(",").mapNotNull {
                val parts = it.split(":")
                if (parts.size == 2) {
                    val foodName = parts[0]
                    val quantity = parts[1].toFloatOrNull()?.toString() ?: return@mapNotNull null
                    foodName to quantity
                } else null
            }

            Log.d("DietViewModel", "Calling getTemp with parsed fwqRaw=$parsed")
            Log.d("DietViewModel", "dietId=$dietId, name=$name")
            getTemp(dietId, parsed, name)
        }
    }

    fun getTemp(dietId: Int, fwqRaw: List<Pair<String, String>>, name: String) {
        viewModelScope.launch {
            val resolved = fwqRaw.mapNotNull { (foodName, quantityStr) ->
                val clean = quantityStr.removeSuffix("g").trim()
                val food = foodRepository.getFood(foodName)
                val quantity = clean.toFloatOrNull()
                if (food != null && quantity != null) {
                    FoodWithQuantity(food, quantity)
                } else {
                    null
                }
            }
            setName(name)
            _selectedFoods.value = (_selectedFoods.value ?: emptyList()) + resolved
            _dietId.value = dietId
        }
    }

    fun addFood(food: Food, quantity: Float) {
        val currentList = _selectedFoods.value.orEmpty().toMutableList()
        val existingIndex = currentList.indexOfFirst { it.food.id == food.id }
        if (existingIndex >= 0) {
            currentList[existingIndex] = FoodWithQuantity(food, quantity)
        } else {
            currentList.add(FoodWithQuantity(food, quantity))
        }
        _selectedFoods.value = currentList
    }

    fun removeFood(item: FoodWithQuantity) {
        _selectedFoods.value = _selectedFoods.value.orEmpty() - item
    }

    fun setName(name: String) {
        _dietName.value = name
    }

    private val _tempSearch = mutableStateOf<String?>(null)
    val tempSearch: State<String?> get() = _tempSearch
    fun getTempSearch(item: String) {
        _tempSearch.value = ""
        _tempSearch.value = item
    }

    fun clearTempSearch() {
        _tempSearch.value = ""
    }

    val totalCalorie: Int
        get() = selectedFoods.sumOf { (it.food.calorie * it.quantity).toInt() }

    val totalCarb: Double
        get() = selectedFoods.sumOf { it.food.carbohydrate * it.quantity.toDouble() }

    val totalProtein: Double
        get() = selectedFoods.sumOf { it.food.protein * it.quantity.toDouble() }

    val totalFat: Double
        get() = selectedFoods.sumOf { it.food.fat * it.quantity.toDouble() }

    private val _getDietSuccessState = mutableStateOf<Boolean?>(false)
    val getDietSuccessState: State<Boolean?> get() = _getDietSuccessState

    private val _getDietState = mutableStateOf<List<MealResponseDto>?>(null)
    val getDietState: State<List<MealResponseDto>?> get() = _getDietState

    fun getDiet() {
        viewModelScope.launch {
            runCatching {
                val uid = requireUserId()
                dietRepository.getTemplate(uid)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    _getDietState.value = body
                    _getDietSuccessState.value = true
                } else {
                    _getDietSuccessState.value = false
                    Log.e("getDiet", "Unsuccessful response: ${response.code()}")
                }
            }.onFailure {
                _getDietSuccessState.value = false
                Log.e("getDiet", it.message ?: "Unknown Error")
            }
        }
    }

    private val _changeDietSuccessState = mutableStateOf<Boolean?>(null)
    val changeDietSuccessState: State<Boolean?> get() = _changeDietSuccessState

    fun editDiet() {
        val foods = selectedFoods.map {
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        viewModelScope.launch {
            runCatching {
                val uid = requireUserId()
                val dietRequest = DietRequestDto(
                    userId = uid,
                    foods = foods,
                    name = dietName.value ?: "식단"
                )
                dietRepository.changeDiet(
                    dietId = dietId.value ?: error("dietId missing"),
                    request = dietRequest
                )
            }.onSuccess {
                _changeDietSuccessState.value = true
                Log.d("editDiet", "success")
            }.onFailure {
                _changeDietSuccessState.value = false
                Log.e("editDiet", it.message ?: "Unknown error")
            }
        }
    }
}
