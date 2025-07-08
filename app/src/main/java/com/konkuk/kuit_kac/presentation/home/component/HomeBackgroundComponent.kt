package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R

@Composable
fun HomeBackgroundComponent(modifier: Modifier = Modifier) {


    Box(
        modifier = Modifier
            .requiredSize(529.dp)
            .clipToBounds()
    ){
        Image(
            modifier = Modifier
                .matchParentSize()
                .blur(radius = 3.799999952316284.dp)
                .offset(y = -19.dp),
            painter = painterResource(R.drawable.img_home_background),
            contentDescription = "homescreen background"
        )
    }
}

