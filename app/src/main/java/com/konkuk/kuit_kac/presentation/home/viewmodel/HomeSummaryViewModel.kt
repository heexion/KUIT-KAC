package com.konkuk.kuit_kac.presentation.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.response.HomeSummaryResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.HomeSummaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSummaryViewModel @Inject constructor(
    private val repository: HomeSummaryRepository
) : ViewModel() {

    private val _summary = mutableStateOf<HomeSummaryResponseDto?>(null)
    val summary: State<HomeSummaryResponseDto?> get() = _summary
    private val _summarySuccessState = mutableStateOf<Boolean?>(null)
    val summarySuccessState: State<Boolean?> get() = _summarySuccessState

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun loadSummary(userId: Int) {
        viewModelScope.launch {
            repository.getHomeSummary(userId)
                .onSuccess { _summary.value = it }
                .onFailure { _error.value = it.message ?: "알 수 없는 오류" }
        }
    }
    fun getSummary(userId: Int){
        viewModelScope.launch {
            runCatching {
                repository.getSummary(userId)
            }.onSuccess { response ->
                if(response.isSuccessful){
                    val body = response.body()
                    _summary.value = body
                    _summarySuccessState.value = true
                }
                else{
                    _summarySuccessState.value = false
                    Log.e("getSummary", "Unsuccessful Response: ${response.code()}")
                }
            }
                .onFailure {
                    _summarySuccessState.value = false
                    Log.e("getSummary", it.message?:"Unknown Error")
                }
        }
    }
}
