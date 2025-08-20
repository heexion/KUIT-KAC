package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.response.NutritionResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.NutritionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val repository: NutritionRepository
) : ViewModel() {

    private val _nutrition = mutableStateOf<NutritionResponseDto?>(null)
    val nutrition: State<NutritionResponseDto?> get() = _nutrition

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun loadNutrition(userId: Int) {
        viewModelScope.launch {
            repository.getNutrition(userId)
                .onSuccess { _nutrition.value = it }
                .onFailure { _error.value = it.message ?: "영양 정보 불러오기 실패" }
        }
    }
}
