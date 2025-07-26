package com.konkuk.kuit_kac.presentation.mealdiet.plan.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun PlanSelectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isSelected: Boolean = false,
    isBlue: Boolean = true,
    isSmall: Boolean = false,
    value: String,
    height: Float = 60f
) {
    var isPressed by remember { mutableStateOf(false) }

    val image = if (isBlue) {
        if (isSmall) {
            if (isPressed || isSelected) R.drawable.bg_plan_blue_small_button_selected
            else R.drawable.bg_plan_blue_small_button_default
        }
        else if (isPressed || isSelected) R.drawable.bg_plan_blue_button_selected
        else R.drawable.bg_plan_blue_button_default
    } else {
        if (isSmall) {
            if (isPressed || isSelected) R.drawable.bg_plan_pink_small_button_selected
            else R.drawable.bg_plan_pink_small_button_default
        }
        else if (isPressed || isSelected) R.drawable.bg_plan_pink_button_selected
        else R.drawable.bg_plan_pink_button_default
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.bhp())
            .pointerInteropFilter {
                when (it.action) {
                    android.view.MotionEvent.ACTION_DOWN -> {
                        isPressed = true
                        true
                    }

                    android.view.MotionEvent.ACTION_UP,
                    android.view.MotionEvent.ACTION_CANCEL -> {
                        isPressed = false
                        onClick()
                        true
                    }

                    else -> false
                }
            }
    ) {
        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo17,
            fontSize = 17f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanSelectButtonPreview() {
    PlanSelectButton(
        isSelected = false,
        isBlue = false,
        value = "분홍/파랑 선택 가능",
        height = 60f
    )
}