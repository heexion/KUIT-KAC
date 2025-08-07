package com.konkuk.kuit_kac.presentation.home.viewmodel

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

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun loadSummary(userId: Int) {
        viewModelScope.launch {
            val result = repository.getHomeSummary(userId)
            result
                .onSuccess { _summary.value = it }
                .onFailure { _error.value = it.message }
        }
    }
}
