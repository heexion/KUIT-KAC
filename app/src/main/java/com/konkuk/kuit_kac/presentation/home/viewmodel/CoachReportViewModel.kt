package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.CoachReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoachReportViewModel @Inject constructor(
    private val repository: CoachReportRepository
) : ViewModel() {

    private val _coachReport = mutableStateOf<CoachReportResponseDto?>(null)
    val coachReport: State<CoachReportResponseDto?> = _coachReport

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadCoachReport(userId: Int) {
        viewModelScope.launch {
            val result = repository.getCoachReport(userId)
            result
                .onSuccess { _coachReport.value = it }
                .onFailure { _error.value = it.message }
        }
    }
}
