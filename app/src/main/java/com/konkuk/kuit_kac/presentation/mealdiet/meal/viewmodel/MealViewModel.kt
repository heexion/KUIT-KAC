package com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository

import android.util.Log
import android.view.View
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.FoodRequestDto
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
    private val mealRepository: MealRepository
):ViewModel() {
    private val _createMealState = mutableStateOf<Boolean?>(null)
    val createMealState: State<Boolean?> get() = _createMealState
    val formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    fun createMeal(
        name: String,
        dietType: String,
    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = MealRequestDto(
            userId = 1,
            dietType = dietType,
            foods = foods,
            name = name,
            dietTime = formattedTime
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

    fun clearFoods() {
        _selectedFoods.clear()
    }
}

data class FoodWithQuantity(
    val food: Food,
    val quantity: Float
)