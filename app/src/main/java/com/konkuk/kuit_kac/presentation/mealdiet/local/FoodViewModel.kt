package com.konkuk.kuit_kac.presentation.mealdiet.local

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.local.dao.FoodDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel@Inject constructor(
    private val repository: FoodRepository
): ViewModel() {
    var query by mutableStateOf("")
    var suggestions by mutableStateOf(listOf<Food>())
        private set

    fun onQueryChange(newQuery: String) {
        query = newQuery
        viewModelScope.launch {
            if (query.length >= 2) {
                suggestions = repository.searchFood("%$query%")
            } else {
                suggestions = emptyList()
            }
        }
    }

    var food by mutableStateOf<Food?>(null)
        private set

    fun loadFoodByName(name: String) {
        viewModelScope.launch {
            food = repository.getFood(name)
        }
    }

}