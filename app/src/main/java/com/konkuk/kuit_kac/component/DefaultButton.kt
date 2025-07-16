package com.konkuk.kuit_kac.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    value: String,
    buttonHeight: Float,
    isOrange: Boolean = false
) {
    val image = if (isOrange) {
        R.drawable.bg_orange_button_default
    } else {
        R.drawable.bg_yellow_button_default
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight.bhp())
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .height(buttonHeight.bhp()),
            painter = painterResource(image),
            contentDescription = "default button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            lineHeight = 28.sp,
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }


}

@Preview
@Composable
private fun DefaultButtonPreview() {
    DefaultButton(
        modifier = Modifier,
        onClick = {},
        value = "일반 버튼(주황/노랑 선택 가능)",
        buttonHeight = 70f,
        isOrange = true
    )
}