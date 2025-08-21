package com.konkuk.kuit_kac.data.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.login.NavigationEvent
import com.konkuk.kuit_kac.data.login.api.UserApiService
import com.konkuk.kuit_kac.data.login.dto.UserResponseDto
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
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

    val incomingKid: StateFlow<String?> = dataStoreRepository.incomingKid

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _userInfo = MutableStateFlow<UserResponseDto?>(null)
    val userInfo = _userInfo.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()


    fun fetchUserInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            _userInfo.value = null
            try {
                val response = apiService.getMe()
                _userInfo.value = response

                if (response.onboardingNeeded == true) {
                    _navigationEvent.emit(NavigationEvent.NavigateToOnboarding)
                } else {
                    _navigationEvent.emit(NavigationEvent.NavigateToHome)
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "유저 정보 로딩 실패", e)
            } finally {
                _isLoading.value = false
            }
        }
    }


//    fun logout() {
//        viewModelScope.launch {
//            dataStoreRepository.clearTokens()
//            _userInfo.value = null
//        }
//    }

    // DataStoreRepository의 KID 채널을 감시
    private fun observeIncomingKid() {
        viewModelScope.launch {
            dataStoreRepository.incomingKid
                .filterNotNull()
                .collect { kid ->
                    Log.d("LoginViewModel", "새로운 KID($kid)를 감지하여 토큰 발급을 시작합니다.")
                    mintToken(kid) // 토큰 발급 함수 호출
                    dataStoreRepository.setIncomingKid(null)
                }
        }
    }

    fun mintToken(uid: String) {
        dataStoreRepository.setIncomingKid(null)

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = apiService.mintToken(uid)
                val accessToken = response.accessToken
//                val refreshToken = response.refreshToken

//                if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                if (!accessToken.isNullOrBlank()) {
                    dataStoreRepository.saveAccessToken(accessToken)
//                    dataStoreRepository.saveRefreshToken(refreshToken)
                    Log.d("LoginViewModel", "민트- 토큰 저장 성공")
                    fetchUserInfo()
                } else {
                    Log.e("LoginViewModel", "서버로부터 받은 토큰이 비어있습니다.")
                }
            } catch (e: Exception) {
//                _userInfo.value = null
                Log.e("LoginViewModel", "토큰 발급 중 예외 발생", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

}
