package com.konkuk.kuit_kac.core.util.context

//Float값을 표현할 때, 소수는 1자리 소수로, 정수는 소수점 없이 나타내도록 해주는 함수
fun Float.pretty(): String {
    return if (this == this.toInt().toFloat()) {
        this.toInt().toString()
    } else {
        String.format("%.1f", this)
    }
}
