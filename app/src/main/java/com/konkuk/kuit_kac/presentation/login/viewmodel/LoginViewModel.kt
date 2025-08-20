package com.konkuk.kuit_kac.presentation.login.viewmodel

//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val api: LoginApiService
//) : ViewModel() {
//
//    var loginState by mutableStateOf<LoginResponse?>(null)
//        private set
//
//    fun handleKakaoCode(code: String) {
//        viewModelScope.launch {
//            try {
//                val response = api.kakaoCallback(code)
//                if (response.isSuccessful) {
//                    loginState = response.body()
//                    // 토큰 저장 (DataStore 등)
//                } else {
//                    Log.e("Login", "서버 오류: ${response.code()}")
//                }
//            } catch (e: Exception) {
//                Log.e("Login", "네트워크 오류", e)
//            }
//        }
//    }
//}
