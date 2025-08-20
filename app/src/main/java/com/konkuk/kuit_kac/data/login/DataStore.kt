package com.konkuk.kuit_kac.data.login

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("auth_prefs")

object TokenKeys {
    val ACCESS_TOKEN = stringPreferencesKey("accessToken")
    val REFRESH_TOKEN = stringPreferencesKey("refreshToken")
    val EXPIRES_IN = intPreferencesKey("expiresIn")
    val ONBOARDING_REQUIRED = booleanPreferencesKey("onboardingRequired")
}