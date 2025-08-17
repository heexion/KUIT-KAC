package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp

@Composable
fun HomeSubBackgroundComponent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .blur(radius = 3.799999952316284f.wp())
                .width(508.22656f.wp())
                .height(195.5752f.bhp()),
            painter = painterResource(R.drawable.img_homegraphscreen_background),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
        )
        Image(
            modifier = Modifier
                .offset(y = -10f.bhp())
                .blur(radius = 3.799999952316284f.wp())
                .width(508.22656f.wp())
                .height(195.5752f.bhp()),
            painter = painterResource(R.drawable.img_homegraphscreen_background),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier
                .offset(y = -20f.bhp())
                .blur(radius = 3.799999952316284f.wp())
                .width(508.22656f.wp())
                .height(195.5752f.bhp()),
            painter = painterResource(R.drawable.img_homegraphscreen_background),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds
        )
    }
}