package com.konkuk.kuit_kac.core.util.context

import android.content.Context
import android.widget.Toast

//토스트 짧게 쓰기 위해 사용
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}