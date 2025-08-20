package com.konkuk.kuit_kac.data.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.api.UserApiService
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class VerifyTokenViewModel @Inject constructor(
//    val userApiService: UserApiService
//) : ViewModel()

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val apiService: UserApiService
) : ViewModel() {

    val accessToken: StateFlow<String?> = dataStoreRepository.getAccessTokenFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _userInfo = MutableStateFlow("")
    val userInfo = _userInfo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchUserInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            _userInfo.value = ""
            try {
                val response = apiService.getMe()
                _userInfo.value = "성공: $response"
            } catch (e: Exception) {
                _userInfo.value = "실패: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStoreRepository.clearTokens()
            _userInfo.value = ""
        }
    }
}