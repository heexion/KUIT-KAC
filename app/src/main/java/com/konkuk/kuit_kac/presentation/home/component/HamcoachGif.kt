package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp

@Composable
fun HamcoachGif(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    num: Int = 1,
    ellipseLength: Double = 147.6,
    mascotLength: Double = 125.0,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(ellipseLength.toFloat().wp(), ellipseLength.toFloat().bhp()),
            painter = painterResource(R.drawable.img_component_ellipse),
            contentDescription = "Nyam Ellipse"
        )

        when (num) {
            1 -> GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().wp(), mascotLength.toFloat().bhp())
                    .offset(x = 5f.wp()),
                drawableId = R.drawable.gif_hamcoach_default,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )

            2 -> GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().wp(), mascotLength.toFloat().bhp())
                    .offset(x = 10f.wp()),
                drawableId = R.drawable.gif_hamcoach_thumbsup,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )
        }
    }
}