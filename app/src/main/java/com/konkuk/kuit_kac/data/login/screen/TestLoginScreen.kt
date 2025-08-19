package com.konkuk.kuit_kac.data.login.screen

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.konkuk.kuit_kac.BuildConfig
import kotlinx.coroutines.launch

@Composable
fun TestLoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    dataStore: DataStore<Preferences>
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            val authUrl = "${BuildConfig.BASE_URL}oauth2/authorization/kakao"
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(context, Uri.parse(authUrl))
        }) {
            Text("카카오 로그인 (CustomTabs)")
        }
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                // 2) JSON로 반환된 토큰을 DataStore에 임시 저장 (디버그용)
                dataStore.edit { prefs ->
                    prefs[stringPreferencesKey("accessToken")] = "디버그 액세스 토큰"
                    prefs[stringPreferencesKey("refreshToken")] = "디버그 리프레시 토큰"
                }

                // 3) 로그인 완료 후 VerifyTokenScreen으로 이동
                navController.navigate("verify")
            }
        }) {
            Text("디버그 토큰 저장 후 VerifyScreen 이동")
        }


        Button(onClick = { navController.navigate("debug") }) {
            Text("다음 (JSON 값)")
        }
    }
}