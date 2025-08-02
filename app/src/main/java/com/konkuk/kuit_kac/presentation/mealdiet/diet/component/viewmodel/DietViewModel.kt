package com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository
):ViewModel() {
    private val _createDietState = mutableStateOf<Boolean?>(null)
    val createDietState: State<Boolean?> get() = _createDietState

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

    private val _dietName = mutableStateOf<String?>("")
    val dietName: State<String?> get() = _dietName

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

}