package com.konkuk.kuit_kac.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import com.konkuk.kuit_kac.data.response.NutritionResponseDto
import com.konkuk.kuit_kac.presentation.home.repository.NutritionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val repository: NutritionRepository,
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

    private val _nutrition = mutableStateOf<NutritionResponseDto?>(null)
    val nutrition: State<NutritionResponseDto?> get() = _nutrition

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    fun loadNutrition() {
        viewModelScope.launch {
            val uid = try { requireUserId() } catch (e: Exception) {
                _error.value = e.message ?: "영양 정보 불러오기 실패"
                return@launch
            }
            val result = repository.getNutrition(uid)
            result.fold(
                onSuccess = { _nutrition.value = it },
                onFailure = { _error.value = it.message ?: "영양 정보 불러오기 실패" }
            )
        }
    }
}
