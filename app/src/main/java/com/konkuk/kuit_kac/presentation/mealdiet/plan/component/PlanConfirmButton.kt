package com.konkuk.kuit_kac.presentation.diet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun PlanConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isAvailable: Boolean = false,
    value: String,
    height: Float = 60f
) {

    val image = if (isAvailable) R.drawable.bg_plan_confirm_button_selected
    else R.drawable.bg_plan_confirm_button_default


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.bhp())
            .clickable { if (isAvailable) onClick() }
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
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanConfirmButtonPreview() {
    PlanConfirmButton(
        isAvailable = false,
        value = "선택가능시 노란색으로 바뀜",
        height = 60f
    )
}