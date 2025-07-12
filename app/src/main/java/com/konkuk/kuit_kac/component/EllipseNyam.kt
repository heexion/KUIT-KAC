package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp

@Composable
fun EllipseNyam(
    modifier: Modifier = Modifier,
    ellipseLength: Double,
    mascotLength: Double
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(ellipseLength.toFloat().wp(), ellipseLength.toFloat().bhp()),
            painter = painterResource(R.drawable.img_component_ellipse),
            contentDescription = "Nyam Ellipse"
        )
        Image(
            modifier = Modifier
                .size(mascotLength.toFloat().wp(), ellipseLength.toFloat().bhp()),
            painter = painterResource(R.drawable.img_component_mascot),
            contentDescription = "NyamCoach Mascot"
        )
    }

}