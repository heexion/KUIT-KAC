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
    val weightInfo: State<WeightResponseDto?>  = _weightInfo

    fun postWeight(userId: Int, weight: Float) {
        viewModelScope.launch {
            val result = runCatching {
                repository.postWeight(WeightRequestDto(userId, weight))
            }
            _postSuccess.value = result.isSuccess
        }
    }

    fun putWeight(userId: Int, weight: Float) {
        viewModelScope.launch {
            repository.putWeight(WeightRequestDto(userId, weight))
        }
    }

    fun getWeight(userId: Int) {
        viewModelScope.launch {
            val result = repository.getWeight(userId)
            if (result.isSuccessful) {
                _weightInfo.value = result.body()
            }
        }
    }
}
