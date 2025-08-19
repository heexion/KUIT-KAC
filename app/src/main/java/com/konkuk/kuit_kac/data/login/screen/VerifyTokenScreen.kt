package com.konkuk.kuit_kac.data.login.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konkuk.kuit_kac.data.login.viewmodel.VerifyTokenViewModel
import kotlinx.coroutines.launch

@Composable
fun VerifyTokenScreen(viewModel: VerifyTokenViewModel = hiltViewModel()) {
    val userApiService = viewModel.userApiService
    val scope = rememberCoroutineScope()
    var result by remember { mutableStateOf("아직 호출 안함") }

    Column(Modifier.padding(16.dp)) {
        Button(onClick = {
            scope.launch {
                try {
                    val user = userApiService.getMe()
                    result = "✅ 성공! 사용자: ${user.height}, id=${user.userId}"
                } catch (e: Exception) {
                    result = "❌ 실패: ${e.message}"
                }
            }
        }) {
            Text("/users/me 호출하기")
        }
        Spacer(Modifier.height(16.dp))
        Text(result)
    }
}