package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun SelectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    value: String,
    buttonHeight: Int,
) {

    val image = if (isSelected) {
        R.drawable.bg_yellow_button_selected
    } else {
        R.drawable.bg_yellow_button_default
    }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight.toFloat().bhp())
            .noRippleClickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .height(buttonHeight.toFloat().bhp()),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            lineHeight = 28f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }


}

@Preview
@Composable
private fun SelectButtonPreview() {
    SelectButton(
        modifier = Modifier,
        onClick = {},
        value = "눌린 상태 유지하는 버튼",
        buttonHeight = 70,
        isSelected = true
    )
}