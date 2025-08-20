package com.konkuk.kuit_kac.data.login.screen

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.kuit_kac.BuildConfig
import com.konkuk.kuit_kac.data.login.viewmodel.LoginViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val accessToken by viewModel.accessToken.collectAsState()
    val userInfo by viewModel.userInfo.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val loginUrl = "${BuildConfig.BASE_URL}oauth2/authorization/kakao"

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("소셜 로그인 테스트 (자동 토큰 갱신)", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        if (accessToken.isNullOrEmpty()) {
            Button(onClick = {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(context, Uri.parse(loginUrl))
            }) {
                Text("카카오 로그인 시작")
            }
        } else {
            Text("로그인 성공!", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Access Token: ${accessToken?.take(20)}...")
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { viewModel.fetchUserInfo() }, enabled = !isLoading) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("보호 API 호출 (/users/me)")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.logout() }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                Text("로그아웃")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (userInfo.isNotEmpty()) {
            Text("API 응답:", style = MaterialTheme.typography.titleMedium)
            Text(userInfo, modifier = Modifier.padding(8.dp))
        }
    }
}
