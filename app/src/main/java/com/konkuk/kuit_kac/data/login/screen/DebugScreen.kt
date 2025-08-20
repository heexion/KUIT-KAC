package com.konkuk.kuit_kac.data.login.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import com.konkuk.kuit_kac.data.login.TokenKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun DebugScreen(
    navController: NavHostController,
    dataStore: DataStore<Preferences>
) {
    var accessToken by remember { mutableStateOf("") }
    var refreshToken by remember { mutableStateOf("") }
    var expiresIn by remember { mutableStateOf("") }
    var onboardingRequired by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = accessToken,
            onValueChange = { accessToken = it },
            label = { Text("accessToken") })
        OutlinedTextField(
            value = refreshToken,
            onValueChange = { refreshToken = it },
            label = { Text("refreshToken") })
        OutlinedTextField(
            value = expiresIn,
            onValueChange = { expiresIn = it },
            label = { Text("expiresIn") })
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = onboardingRequired, onCheckedChange = { onboardingRequired = it })
            Text("onboardingRequired")
        }
        Button(onClick = {
            scope.launch {
                dataStore.edit { prefs ->
                    prefs[TokenKeys.ACCESS_TOKEN] = accessToken
                    prefs[TokenKeys.REFRESH_TOKEN] = refreshToken
                    prefs[TokenKeys.EXPIRES_IN] = expiresIn.toIntOrNull() ?: 0
                    prefs[TokenKeys.ONBOARDING_REQUIRED] = onboardingRequired
                }

                // 값 잘 저장되는지 테스트
                val value = dataStore.data.first()[TokenKeys.ACCESS_TOKEN]
                Log.d("DataStoreCheck", "저장 후 값: $value")

//                navController.navigate("verify")
            }
        }) {
            Text("저장 후 검증 화면으로")
        }
    }
}
