package com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository,
    private val foodRepository: FoodRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _createDietState = mutableStateOf<Boolean?>(null)
    val createDietState: State<Boolean?> get() = _createDietState

    private val _dietId = mutableStateOf<Int?>(null)
    val dietId: State<Int?> get() = _dietId

    private val _dietName = mutableStateOf<String?>("")
    val dietName: State<String?> get() = _dietName

    fun createDiet(

    ){
        val foods = selectedFoods.map {
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = DietRequestDto(
            userId = 1,
            name = dietName.value?:"",
            foods = foods
        )
        viewModelScope.launch {
            runCatching {
                dietRepository.createDiet(request = dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _createDietState.value = true
                    Log.e("createDiet", "success")
                }
                .onFailure {
                    _createDietState.value = false
                    Log.e("creatDiet", it.message?: "Unknown error")
                }
        }
    }
    private val _selectedFoods = mutableStateListOf<FoodWithQuantity>()
    val selectedFoods: List<FoodWithQuantity> get() = _selectedFoods

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

            Log.d("MealViewModel", "Calling getTemp with parsed fwqRaw=$parsed")
            Log.d("frao", "dietId=$dietId, name=$name")
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
            _selectedFoods.addAll(resolved)
            _dietId.value = dietId
        }
    }

    fun addFood(food: Food, quantity: Float) {
        val existing = _selectedFoods.indexOfFirst { it.food.id == food.id }
        if (existing >= 0) {
            _selectedFoods[existing] = FoodWithQuantity(food, quantity)
        } else {
            _selectedFoods.add(FoodWithQuantity(food, quantity))
        }
    }
    fun removeFood(item: FoodWithQuantity) {
        _selectedFoods.remove(item)
    }


    fun setName(
        name: String
    ){
        _dietName.value =name
    }
    val totalCalorie: Int
        get() = selectedFoods.sumOf { (it.food.calorie * it.quantity).toInt() }

    val totalCarb: Double
        get() = selectedFoods.sumOf { it.food.carb * it.quantity }

    val totalProtein: Double
        get() = selectedFoods.sumOf { it.food.protein * it.quantity }

    val totalFat: Double
        get() = selectedFoods.sumOf { it.food.fat * it.quantity }

    private val _getDietSuccessState = mutableStateOf<Boolean?>(false)
    val getDietSuccessState: State<Boolean?> get() = _getDietSuccessState
    private val _getDietState = mutableStateOf<List<MealResponseDto>?>(null)
    val getDietState: State<List<MealResponseDto>?> get() = _getDietState

    fun getDiet(userId: Int){
        viewModelScope.launch {
            runCatching {
                dietRepository.getTemplate(userId)
            }.onSuccess { response ->
                    if(response.isSuccessful){
                        val body = response.body()
                        _getDietState.value = body
                        _getDietSuccessState.value = true
                    }
                }
                .onFailure {
                    _getDietSuccessState.value = false
                    Log.e("getDiet", it.message?: "Unknown Error")
                }
        }
    }
    private val _changeDietSuccessState = mutableStateOf<Boolean?>(null)
    val changeDietSuccessState: State<Boolean?> get() = _changeDietSuccessState
    fun editDiet(

    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = DietRequestDto(
            userId = 1,
            foods = foods,
            name = dietName.value?:"식단"
        )
        viewModelScope.launch {
            runCatching {
                dietRepository.changeDiet(dietId = dietId.value?:1,dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _changeDietSuccessState.value = true
                    Log.e("editDiet", "success")
                }
                .onFailure {
                    _changeDietSuccessState.value = false
                    Log.e("editDiet", it.message?: "Unknown error")
                }
        }
    }

}