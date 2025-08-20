package com.konkuk.kuit_kac.presentation.mealdiet.local

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.dao.HangulSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningReduce
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
            suggestions = repository.searchSmart(query)
        }
    }


    var food by mutableStateOf<Food?>(null)
        private set

    fun loadFoodByName(name: String) {
        viewModelScope.launch {
            food = repository.getFood(name)
        }
    }

    suspend fun getFoodByName(name: String): Food? {
        return repository.getFood(name)
    }

}