package com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel

import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository

import android.util.Log
import android.view.View
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
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
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository
):ViewModel() {
    private val _createMealState = mutableStateOf<Boolean?>(null)
    val createMealState: State<Boolean?> get() = _createMealState

    fun createMeal(
        name: String,
        dietType: String,
        foods: List<FoodRequestDto>
    ){
        val dietRequest = MealRequestDto(
            userId = 5040,
            dietType = dietType,
            foods = foods,
            name = name,
            dietTime = Instant.now()
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.createMeal(dietRequest)
            }
                .onSuccess {
                    _createMealState.value = true
                }
                .onFailure {
                    _createMealState.value = false
                    Log.e("creatDiet", error(message = "error")?: "Unknown error")
                }
        }
    }
    private val _selectedFoods = mutableStateOf<List<Food>>(emptyList())
    val selectedFoods: List<Food> get() = _selectedFoods.value

    fun addFood(food: Food) {
        _selectedFoods.value = _selectedFoods.value + food
    }

    fun removeFood(food: Food) {
        _selectedFoods.value = _selectedFoods.value - food
    }

    fun clearFoods() {
        _selectedFoods.value = emptyList()
    }
}