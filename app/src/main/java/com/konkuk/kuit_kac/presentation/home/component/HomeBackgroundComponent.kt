package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.deepYellow

@Composable
fun HomeBackgroundComponent(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = Modifier
            .offset(y = -30f.bhp())
            .fillMaxWidth()
            .requiredSize(412f.wp(), 529f.bhp())
            .clipToBounds()
    ){
        Column(
            modifier = Modifier
                .matchParentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) { Image(
            modifier = Modifier
                .requiredSize(750f.wp(),317.2f.bhp()),
            painter = painterResource(R.drawable.home_background_top),
            contentDescription = "homescreen background"
        )
            Image(
                modifier = Modifier
                    .requiredSize(529f.wp(), 264.5f.bhp()),
                painter = painterResource(R.drawable.home_background_bottom),
                contentDescription = "homescreen background"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .width(133f.wp())
                    .height(284f.bhp()),
                painter = painterResource(R.drawable.home_background_left),
                contentDescription = "curtain"
            )
            Image(
                modifier = Modifier
                    .width(133f.wp())
                    .height(284f.bhp()),
                painter = painterResource(R.drawable.home_background_right),
                contentDescription = "curtain"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(245f.wp())
                    .height(200f.bhp()),
                painter = painterResource(R.drawable.home_background_window),
                contentDescription = "window"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 340f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(325f.wp())
                    .height(148f.bhp())
                    .padding(top = 0f.bhp()),
                painter = painterResource(R.drawable.home_background_carpet),
                contentDescription = "carpet"
            )
        }
    }
}

