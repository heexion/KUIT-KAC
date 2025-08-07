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
): ViewModel() {

    private val _nutrition = mutableStateOf<NutritionResponseDto?>(null)
    val nutrition: State<NutritionResponseDto?> get() = _nutrition

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun loadNutrition(userId: Int) {
        viewModelScope.launch {
            val result = repository.getNutrition(userId)
            result
                .onSuccess { response ->
                    _nutrition.value = response
                }
                .onFailure { throwable ->
                    _error.value = "영양 정보 불러오기 실패: ${throwable.message}"
                }
        }
    }
}
