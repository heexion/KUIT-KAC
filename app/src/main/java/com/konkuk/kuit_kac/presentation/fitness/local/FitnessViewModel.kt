package com.konkuk.kuit_kac.presentation.fitness.local

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitnessViewModel@Inject constructor(
    private val repository: FitnessRepository
): ViewModel() {
    var query by mutableStateOf("")
    var suggestions by mutableStateOf(listOf<Fitness>())
        private set

    fun onQueryChange(newQuery: String) {
        query = newQuery
        viewModelScope.launch {
            if (query.length >= 2) {
                suggestions = repository.searchFitness("%$query%")
            } else {
                suggestions = emptyList()
            }
        }
    }

    var fitness by mutableStateOf<Fitness?>(null)
        private set

    fun loadFitnessByName(name: String) {
        viewModelScope.launch {
            fitness = repository.getFitness(name)
        }
    }
    suspend fun getFitnessByName(name: String): Fitness? {
        return repository.getFitness(name)
    }

}