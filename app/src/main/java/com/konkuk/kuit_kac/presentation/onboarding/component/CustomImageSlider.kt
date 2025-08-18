package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import kotlin.math.roundToInt

@Composable
fun CustomImageSlider(
    modifier: Modifier = Modifier,
    currentValue: Int,
    onValueChange: (Int) -> Unit
) {
    val totalSteps = 5
    val sliderWidth = 280f.wp()
    val knobSize = 32f.bhp()
    val extraTouchArea = 6f.bhp() //  손잡이 눌림 영역 확장

    var dragOffset by remember { mutableStateOf(0f) }

    val density = LocalDensity.current
    val stepWidthPx = with(density) {
        (sliderWidth - knobSize).toPx() / (totalSteps - 1)
    }

    val imageRes = when (currentValue) {
        0 -> R.drawable.ic_input_bar
        1 -> R.drawable.ic_input_bar_1
        2 -> R.drawable.ic_input_bar_2
        3 -> R.drawable.ic_input_bar_3
        4 -> R.drawable.ic_input_bar_4
        else -> R.drawable.ic_input_bar
    }

    // 슬라이더 전체 컨테이너
    Box(
        modifier = modifier
            .width(sliderWidth)
            .height(40f.bhp()),
        contentAlignment = Alignment.CenterStart
    ) {
        // 배경 이미지 (단계별 전체 막대 이미지)
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Slider Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(30f.bhp())
        )

        // 움직일 썸(손잡이) 버튼
        Box(
            modifier = Modifier
                .offset { IntOffset(x = dragOffset.roundToInt(), y = 0) }
                .size(knobSize + extraTouchArea)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        dragOffset = (dragOffset + dragAmount.x)
                            .coerceIn(0f, stepWidthPx * (totalSteps - 1))
                        val newStep = (dragOffset / stepWidthPx).roundToInt()
                        onValueChange(newStep)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            // 손잡이 이미지
            Image(
                painter = painterResource(id = R.drawable.ic_input_slider_knob),
                contentDescription = "Knob",
                modifier = Modifier
                    .size(knobSize) // 실제 보이는 크기는 knobSize만 유지
                    .alpha(0f)
            )
        }
    }
}


@Preview
@Composable
private fun SliderPreview() {
    var activityLevel by remember { mutableStateOf(0) }

    CustomImageSlider(
        currentValue = activityLevel,
        onValueChange = { activityLevel = it }
    )

}