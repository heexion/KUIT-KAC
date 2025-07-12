package com.konkuk.kuit_kac.core.util.context

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun Float.wp(): Dp {
    val windowSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    fun paddingWidth(width:  Float): Dp {
        return with(density) {(windowSize.width * width).toDp()}
    }
    return paddingWidth(this@wp / 412)
}

@Composable
fun Float.hp(): Dp {
    val windowSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    val topPadding = WindowInsets.statusBars
        .asPaddingValues()
        .calculateTopPadding()
    fun paddingHeight(height: Float): Dp {
        return with(density) {(windowSize.height * height).toDp()}
    }
    return paddingHeight(this@hp / 829) + topPadding
}

// 사이즈 정할 때는 hp 대신 무조건 이걸로
@Composable
fun Float.bhp(): Dp {
    val windowSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    fun paddingHeight(height: Float): Dp {
        return with(density) {(windowSize.height * height).toDp()}
    }
    return paddingHeight(this@bhp / 829)
}

@Composable
fun Float.isp(): TextUnit {
    val windowSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    fun spMake(size: Float): TextUnit {
        return with(density) {(windowSize.height * size).toSp()}
    }
    return spMake(this@isp / 829)
}
