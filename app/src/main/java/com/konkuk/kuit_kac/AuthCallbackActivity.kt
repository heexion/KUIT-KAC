package com.konkuk.kuit_kac

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.konkuk.kuit_kac.data.login.dataStore
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import kotlinx.coroutines.launch

// 딥링크 연결용 액티비티
class AuthCallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DEEPLINK_TEST", "AuthCallbackActivity가 성공적으로 실행되었습니다!")


        // Hilt를 사용하지 않는 Activity에서 DataStore에 접근
        val dataStore = applicationContext.dataStore
        val repository = DataStoreRepository(dataStore)

        val data: Uri = intent.data ?: return
        if (data.scheme == "myapp" && data.host == "oauth" && data.path == "/kakao") {
            val access = data.getQueryParameter("access")
            val refresh = data.getQueryParameter("refresh")

            if (!access.isNullOrBlank() && !refresh.isNullOrBlank()) {
                // DataStore는 비동기 작업이므로 CoroutineScope 필요
                lifecycleScope.launch {
                    repository.saveAccessToken(refresh)
                    repository.saveRefreshToken(refresh)
                    // 저장이 완료되면 Activity 종료
                    finish()
                }
            } else {
                // 토큰이 없는 경우 바로 종료
                finish()
            }
        } else {
            finish()
        }
    }
}