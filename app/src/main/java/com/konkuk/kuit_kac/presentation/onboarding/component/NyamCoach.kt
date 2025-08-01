package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import com.konkuk.kuit_kac.R

@Composable
fun NyamCoach(
    modifier: Modifier = Modifier,
    alpha: Float = 1f // 0f ~ 1f 범위
) {
    Image(
        painter = painterResource(id = R.drawable.img_nyamee_normal),
        contentDescription = "냠코치",
        modifier = modifier
            .graphicsLayer(alpha = alpha)
    )
}
