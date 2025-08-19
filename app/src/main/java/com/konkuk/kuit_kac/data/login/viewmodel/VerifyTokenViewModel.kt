package com.konkuk.kuit_kac.data.login.viewmodel

import androidx.lifecycle.ViewModel
import com.konkuk.kuit_kac.data.login.api.UserApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyTokenViewModel @Inject constructor(
    val userApiService: UserApiService
) : ViewModel()