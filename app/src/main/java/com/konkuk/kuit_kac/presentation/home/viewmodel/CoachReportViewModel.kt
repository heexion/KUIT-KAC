package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.CoachReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoachReportViewModel @Inject constructor(
    private val repository: CoachReportRepository,
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

    private val _coachReport = mutableStateOf<CoachReportResponseDto?>(null)
    val coachReport: State<CoachReportResponseDto?> = _coachReport

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadCoachReport() {
        viewModelScope.launch {
            val uid = requireUserId()
            repository.getCoachReport(uid)
                .fold(
                    onSuccess = { _coachReport.value = it },
                    onFailure = { _error.value = it.message }
                )
        }
    }
}
