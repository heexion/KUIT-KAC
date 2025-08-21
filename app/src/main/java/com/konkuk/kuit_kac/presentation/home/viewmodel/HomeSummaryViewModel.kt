package com.konkuk.kuit_kac.presentation.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.response.HomeSummaryResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.HomeSummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSummaryViewModel @Inject constructor(
    private val repository: HomeSummaryRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _userId = mutableStateOf<Int?>(null)
    val userId: State<Int?> get() = _userId

    init {
        viewModelScope.launch {
            dataStoreRepository.getUserIdFlow().collect { _userId.value = it }
        }
    }
    private suspend fun requireUserId(): Int =
        _userId.value ?: dataStoreRepository.getUserIdFlow().first()
        ?: throw IllegalStateException("User id is null")

    private val _summary = mutableStateOf<HomeSummaryResponseDto?>(null)
    val summary: State<HomeSummaryResponseDto?> get() = _summary

    private val _summarySuccessState = mutableStateOf<Boolean?>(null)
    val summarySuccessState: State<Boolean?> get() = _summarySuccessState

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun getSummary() {
        viewModelScope.launch {
            runCatching {
                val uid = requireUserId()
                repository.getSummary(uid)
            }.onSuccess { response ->
                Log.d("getSummary", response.body()?.remainingKCalorie.toString())
                if (response.isSuccessful) {
                    _summary.value = response.body()
                    _summarySuccessState.value = true
                } else {
                    _summarySuccessState.value = false
                    Log.e("getSummary", "Unsuccessful Response: ${response.code()}")
                }
            }.onFailure {
                _summarySuccessState.value = false
                Log.e("getSummary", it.message ?: "Unknown Error")
            }
        }
    }
}
