package com.konkuk.kuit_kac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.konkuk.kuit_kac.data.login.repository.DataStoreRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

// 딥링크 연결용 액티비티
@EntryPoint
@InstallIn(SingletonComponent::class)
interface AuthCallbackEntryPoint {
    fun getDataStoreRepository(): DataStoreRepository
}

class AuthCallbackActivity : AppCompatActivity() {
    companion object {
        const val KID_EXTRA = "kakao_id_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DEEPLINK_TEST", "AuthCallbackActivity가 성공적으로 실행되었습니다!")

        val data: Uri = intent.data ?: run {
            finish()
            return
        }

        if (data.scheme == "myapp" && data.host == "oauth" && data.path == "/kakao") {
            val kid = data.getQueryParameter("kid")

            if (!kid.isNullOrBlank()) {
                Log.d("AuthCallback", "KID 수신 성공: $kid")

                val hiltEntryPoint = EntryPointAccessors.fromApplication(
                    applicationContext,
                    AuthCallbackEntryPoint::class.java
                )
                val repository = hiltEntryPoint.getDataStoreRepository()
                repository.setIncomingUid(kid)

                val appIntent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                    startActivity(appIntent)

            } else {
                Log.e("AuthCallback", "딥링크에 KID가 없습니다.")
            }
        }
        // 역할 끝-> 즉시 종료
        finish()
    }
}