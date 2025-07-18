package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo27
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScaleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    weight: Double = 0.0
) {
    val coroutineScope = rememberCoroutineScope()
    val clicked = remember { mutableStateOf(false) }
    var buttonText = "수정하기"
    var scaleText = "${weight}kg"
    var textSize = DungGeunMo35

    if (weight == 0.0) {
        buttonText = "기록하기"
        scaleText = "기록 되지 않음!"
        textSize = DungGeunMo27
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .requiredSize(588.dp)
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .offset(y = 569.dp)
        ) {
            Image(
                modifier = Modifier
                    .blur(radius = 3.799999952316284.dp)
                    .width(508.22656.dp)
                    .height(195.5752.dp),
                painter = painterResource(R.drawable.img_homegraphscreen_background),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds,
            )
            Image(
                modifier = Modifier
                    .offset(y = (-10).dp)
                    .blur(radius = 3.799999952316284.dp)
                    .width(508.22656.dp)
                    .height(195.5752.dp),
                painter = painterResource(R.drawable.img_homegraphscreen_background),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds
            )
        }
        val scale = remember { Animatable(1f) }
        LaunchedEffect(clicked.value) {
            if(clicked.value){
                delay(500)
                scale.animateTo(5f, animationSpec = tween(800))
            }
            }
        Image(
            modifier = Modifier
                .zIndex(if (clicked.value) 2f else 0f)
                .size(165.563.dp,93.21614.dp)
                .offset(y = 361.25.dp)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                },
            painter = painterResource(R.drawable.img_home_scale),
            contentDescription = "scale"
        )
        Image(
            modifier = Modifier
                .padding(top = 102.dp)
                .size(290.dp),
            painter = painterResource(id = R.drawable.img_nyamee_normal),
            contentDescription = "homescale scale bg",
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 103.dp, width = 275.dp)
                    .padding(end = 18.dp, top = 19.58.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "조금 더 힘내면 원하는 몸무게에 금방 도달 할 수 있을 거야",
                style = DungGeunMo15,
                lineHeight = 20.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(275.dp)
                    .padding(top = 41.42.dp, start = 10.dp, end = 26.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 450.dp
                    )
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.25.dp,
                        Color.Black,
                        RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
                    )
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_weight),
                        contentDescription = null,
                        modifier = Modifier
                            .width(300.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = "<현재 체중>",
                        style = DungGeunMo24,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 55.dp)
                    )
                    Text(
                        text = scaleText,
                        style = textSize,
                        color = Color(0xFF713E3A),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 25.dp)
                    )
                }


                Button(
                    onClick = {
                        clicked.value = true
                        coroutineScope.launch {
                            delay(800)
                            navController.navigate(Route.HomeScaleInput.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                ) {
                    Text(
                        text = buttonText,
                        style = DungGeunMo20,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScalePreview() {
    val navController = rememberNavController()
    HomeScaleScreen(
        modifier = Modifier,
        navController = navController,
        weight = 2.4
    )
}