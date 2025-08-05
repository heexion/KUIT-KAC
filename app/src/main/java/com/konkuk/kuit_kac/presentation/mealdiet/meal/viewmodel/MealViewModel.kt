package com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository

import android.util.Log
import android.view.View
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.core.util.context.toDrawable
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _createMealState = mutableStateOf<Boolean?>(null)
    val createMealState: State<Boolean?> get() = _createMealState
    val formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    private val _changeRecordSuccessState = mutableStateOf<Boolean?>(null)
    val changeRecordSuccessState: State<Boolean?> get() = _changeRecordSuccessState

    private val _selectType = mutableStateOf<String?>(null)
    val selectType: State<String?> get() = _selectType


    fun editMeal(

    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = MealRequestDto(
            userId = 1,
            dietType = selectType.value?:"",
            foods = foods,
            name = selectType.value?:"" + "식단",
            dietTime = _selectedTime.value ?: formattedTime
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.changeRecord(dietId = dietId.value?:1,dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _changeRecordSuccessState.value = true
                    Log.e("editDiet", "success")
                }
                .onFailure {
                    _changeRecordSuccessState.value = false
                    Log.e("editDiet", it.message?: "Unknown error")
                }
        }
    }

    fun createMeal(
    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = MealRequestDto(
            userId = 1,
            dietType = selectType.value?:"",
            foods = foods,
            name = selectType.value?:"" + "식단",
            dietTime = _selectedTime.value ?: formattedTime
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.createMeal(dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _createMealState.value = true
                    Log.e("createDiet", "success")
                }
                .onFailure {
                    _createMealState.value = false
                    Log.e("creatDiet", it.message?: "Unknown error")
                }
        }
    }
    private val _selectedFoods = mutableStateListOf<FoodWithQuantity>()
    val selectedFoods: List<FoodWithQuantity> get() = _selectedFoods

    init {
        val dietId = savedStateHandle.get<Int>("dietId") ?: -1
        val fwqRawString = savedStateHandle.get<String>("fwqRaw") ?: ""
        val mealType = savedStateHandle.get<String>("mealType") ?: ""

        if(mealType.isNotEmpty()){
            setType(mealType)
        }

        Log.d("MealViewModel", "dietId=$dietId, fwqRawString=$fwqRawString")

        if (dietId != -1 && fwqRawString.isNotEmpty()) {
            val parsed = fwqRawString.split(",").mapNotNull {
                val parts = it.split(":")
                if (parts.size == 2) {
                    val name = parts[0]
                    val quantity = parts[1].toFloatOrNull()?.toString() ?: return@mapNotNull null
                    name to quantity
                } else null
            }

            Log.d("MealViewModel", "Calling getTemp with parsed fwqRaw=$parsed")
            getTemp(dietId, parsed)
        }
    }

    fun getTemp(dietId: Int, fwqRaw: List<Pair<String, String>>) {
        viewModelScope.launch {
            val resolved = fwqRaw.mapNotNull { (name, quantityStr) ->
                val clean = quantityStr.removeSuffix("g").trim()
                val food = foodRepository.getFood(name)
                val quantity = clean.toFloatOrNull()
                if (food != null && quantity != null) {
                    FoodWithQuantity(food, quantity)
                } else {
                    null
                }
            }
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

    private val _selectedTime = mutableStateOf<String?>(null)
    val selectedTime: State<String?> get() = _selectedTime

    fun setMealTime(hour: String, minute: String, isAM: Boolean) {
        val h = hour.toInt().let {
            if (isAM && it == 12) 0
            else if (!isAM && it != 12) it + 12
            else it
        }.toString().padStart(2, '0')

        val formatted = "$h:${minute.padStart(2, '0')}:00"
        _selectedTime.value = formatted
    }

    fun setType(type: String){
        _selectType.value = type
    }

    fun removeFood(item: FoodWithQuantity) {
        _selectedFoods.remove(item)
    }

    fun clearFoods() {
        _selectedFoods.clear()
    }

    private val _getRecordSuccessState = mutableStateOf<Boolean?>(null)
    val getRecordSuccessState: State<Boolean?> get() = _getRecordSuccessState

    private val _getRecordState = mutableStateOf<List<MealResponseDto>?>(null)
    val getRecordState: State<List<MealResponseDto>?> get() = _getRecordState

    fun getRecord(userId: Int) {
        viewModelScope.launch {
            runCatching {
                mealRepository.getRecord(userId)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    _getRecordState.value = body
                    _mealCardData.value = body?.map { parseToMealCardData(it) } ?: emptyList()
                    _getRecordSuccessState.value = true
                } else {
                    _getRecordSuccessState.value = false
                    Log.e("getRecord", "Unsuccessful response: ${response.code()}")
                }
            }.onFailure {
                _getRecordSuccessState.value = false
                Log.e("getRecord", it.message ?: "Unknown error")
            }
        }
    }
    private val _mealCardData = mutableStateOf<List<MealCardData>>(emptyList())
    val mealCardData: State<List<MealCardData>> = _mealCardData
    fun parseToMealCardData(dto: MealResponseDto): MealCardData {
        return MealCardData(
            dietId = dto.id,
            mealType = dto.dietType,
            totalKcal = "${dto.totalKcal}kcal",
            foodList = dto.dietFoods.map { foodItem ->
                Triple(
                    foodItem.food.foodType.toDrawable(),
                    foodItem.food.name,
                    "${foodItem.quantity}g"
                )
            }
        )
    }
    val totalCarb: Double
        get() = selectedFoods.sumOf { it.food.carb * it.quantity }

    val totalProtein: Double
        get() = selectedFoods.sumOf { it.food.protein * it.quantity }

    val totalFat: Double
        get() = selectedFoods.sumOf { it.food.fat * it.quantity }

    private val _getChangeSuccessState = mutableStateOf<Boolean?>(null)
    val getChangeSuccessState: State<Boolean?> get() = _getChangeSuccessState

    private val _dietId = mutableStateOf<Int?>(null)
    val dietId: State<Int?> get() = _dietId


}

data class FoodWithQuantity(
    val food: Food,
    val quantity: Float
)

data class MealCardData(
    val dietId: Int,
    val mealType: String,
    val totalKcal: String,
    val foodList: List<Triple<Int, String, String>>,
    val isPlanned: Boolean = false
)