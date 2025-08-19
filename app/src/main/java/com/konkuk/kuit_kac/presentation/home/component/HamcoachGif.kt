package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
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
                .size(ellipseLength.toFloat().bhp(), ellipseLength.toFloat().bhp()),
            painter = painterResource(R.drawable.img_component_ellipse),
            contentDescription = "Nyam Ellipse"
        )
        // 1:기본 2:엄지척 3:화남 4:고민중(로딩)
        when (num) {
            1 -> GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().bhp(), mascotLength.toFloat().bhp()),
//                    .offset(x = 5.dp),
                drawableId = R.drawable.gif_hamcoach_default,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )

            2 -> GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().bhp(), mascotLength.toFloat().bhp()),
//                    .offset(x = 10.dp),
                drawableId = R.drawable.gif_hamcoach_thumbsup,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )

            3 -> GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().bhp(), mascotLength.toFloat().bhp()),
//                    .offset(x = 10f.dp),
                drawableId = R.drawable.gif_hamcoach_angry,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )

            4->  GifImage(
                modifier = Modifier
                    .size(mascotLength.toFloat().bhp(), mascotLength.toFloat().bhp()),
//                    .offset(x = 10.dp, y = (-10).dp),
                drawableId = R.drawable.gif_hamcoach_thinking_loading,
                onClick = { onClick() },
                actualWidth = 88.43783f.wp(),
                actualHeight = 88.43783f.bhp()
            )
        }
    }
}