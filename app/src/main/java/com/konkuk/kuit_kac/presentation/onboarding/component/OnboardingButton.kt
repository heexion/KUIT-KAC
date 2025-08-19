package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun OnboardingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    value: String,
    height: Float = 60f
) {
    val image = R.drawable.img_onboarding_defalut_button

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24f.wp())
            .height(height.bhp())
            .noRippleClickable { onClick() }
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-2f).bhp())
        )
    }
}
