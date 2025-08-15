package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.WeightRequestDto
import com.konkuk.kuit_kac.data.response.WeightResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.HomeWeightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val repository: HomeWeightRepository
) : ViewModel() {

    private val _postSuccess = mutableStateOf<Boolean?>(null)
    val postSuccess: State<Boolean?> = _postSuccess

    private val _weightInfo = mutableStateOf<WeightResponseDto?>(null)
    val weightInfo: State<WeightResponseDto?> = _weightInfo

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun postWeight(userId: Int, weight: Float) {
        viewModelScope.launch {
            repository.postWeight(WeightRequestDto(userId, weight))
                .onSuccess { _postSuccess.value = true }
                .onFailure { e ->
                    _postSuccess.value = false
                    _error.value = e.message
                }
        }
    }

    fun putWeight(userId: Int, weight: Float) {
        viewModelScope.launch {
            repository.putWeight(WeightRequestDto(userId, weight))
                .onFailure { e -> _error.value = e.message }
        }
    }

    fun getWeight(userId: Int) {
        viewModelScope.launch {
            repository.getWeight(userId)
                .onSuccess { _weightInfo.value = it }
                .onFailure { e ->
                    _weightInfo.value = WeightResponseDto(0f, "")
                    _error.value = e.message
                }
        }
    }
}