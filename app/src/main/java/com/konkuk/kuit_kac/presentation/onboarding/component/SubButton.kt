package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun SubButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    value: String,
    buttonHeight: Int,
    buttonWidth: Int
) {

    val image = if (isSelected) {
        R.drawable.ic_sub_select_button
    } else {
        R.drawable.ic_sub_button
    }


    Box(
        modifier = modifier
            .height(buttonHeight.toFloat().bhp())
            .noRippleClickable { onClick() }
    ) {
        Image(
            modifier = Modifier
                .width(buttonWidth.toFloat().wp())
                .align(Alignment.Center)
                .height(buttonHeight.toFloat().bhp()),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 17f.isp(),
            lineHeight = 28f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun SubButtonPreview_Unselected() {
    SubButton(
        value = "여자",
        buttonHeight = 60,
        buttonWidth = 100,
        isSelected = true,
        onClick = {}
    )
}


