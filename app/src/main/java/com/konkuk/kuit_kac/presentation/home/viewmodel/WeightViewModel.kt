package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.request.WeightRequestDto
import com.konkuk.kuit_kac.data.response.WeightResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.HomeWeightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val repository: HomeWeightRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> = _userId

    init {
        viewModelScope.launch {
            dataStoreRepository.getUserIdFlow().collect { _userId.value = it }
        }
    }
    private suspend fun requireUserId(): Int =
        _userId.value ?: dataStoreRepository.getUserIdFlow().first()
        ?: throw IllegalStateException("User id is null")

    private val _postSuccess = mutableStateOf<Boolean?>(null)
    val postSuccess: State<Boolean?> = _postSuccess

    private val _weightInfo = mutableStateOf<WeightResponseDto?>(null)
    val weightInfo: State<WeightResponseDto?> = _weightInfo

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun postWeight(weight: Float) {
        viewModelScope.launch {
            val uid = requireUserId()
            repository.postWeight(WeightRequestDto(uid, weight))
                .fold(
                    onSuccess = { _postSuccess.value = true },
                    onFailure = { e ->
                        _postSuccess.value = false
                        _error.value = e.message
                    }
                )
        }
    }


    fun getWeight() {
        viewModelScope.launch {
            val uid = requireUserId()
            repository.getWeight(uid)
                .fold(
                    onSuccess = { _weightInfo.value = it },
                    onFailure = { e ->
                        _weightInfo.value = WeightResponseDto(0f, "")
                        _error.value = e.message
                    }
                )
        }
    }

}
