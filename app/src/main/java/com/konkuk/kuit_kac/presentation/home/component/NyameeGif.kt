package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.wp
import kotlin.random.Random

@Composable
fun NyameeGif(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    num: Int = 1,
    sizePercent: Float = 1f
) {
//    val randNum = Random.nextInt(3) + 1

    Box(
        modifier = modifier
    ) {
        when (num) {
            // 1: 팔굽혀펴기 2: 헤드셋 3: 덤벨
            1 -> GifImage(
                modifier = Modifier
//                    .size(375.2f.wp(), 488f.bhp())
                    .offset(x = 0f.wp(), y = 0f.bhp()),
                drawableId = R.drawable.gif_nyamee_jumpingjacks_effect,
                onClick = { onClick() },
                actualWidth = 187.6f.wp(),
                actualHeight = 244f.bhp(),
                size = sizePercent
            )

            2 -> GifImage(
                modifier = Modifier
//                    .size(260.16f.wp(), 360.4f.bhp())
                    .offset(x = -5f.wp(), y = 10f.bhp()),
                drawableId = R.drawable.gif_nyamee_headset,
                onClick = { onClick() },
                actualWidth = 187.6f.wp(),
                actualHeight = 244f.bhp(), size = sizePercent * 0.69f
            )

            3 -> GifImage(
                modifier = Modifier,
//                    .size(324.2f.wp(), 400f.bhp())
//                    .offset(x = 25f.wp(), y = 29f.hp()),
                drawableId = R.drawable.gif_nyamee_dumbbell,
                onClick = { onClick() },
                actualWidth = 187.6f.wp(),
                actualHeight = 244f.bhp(), size = sizePercent*0.86f
            )
        }
    }
}