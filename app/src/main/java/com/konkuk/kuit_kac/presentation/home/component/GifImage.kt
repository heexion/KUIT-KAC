package com.konkuk.kuit_kac.presentation.home.component

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size


@Composable
fun GifImage(
    modifier: Modifier = Modifier,
    drawableId: Int,
    onClick: () -> Unit = {},
    actualWidth: Dp,
    actualHeight: Dp,
    size: Float = 1f,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Box(modifier = modifier) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(drawableId).apply {
                    size(Size.ORIGINAL)
                }.build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier.graphicsLayer {
                scaleX = size
                scaleY = size
            }
        )

        // 실제 클릭 부분
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(actualWidth, actualHeight)
                .clickable(
                    enabled = onClick != {},
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    onClick()
                }
//                .background(Color(0x30000000))
                .background(Color.Transparent),

            )
    }
}