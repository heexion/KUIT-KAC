package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
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
        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 477.73f.hp())
        )
        val scale = remember { Animatable(1f) }
        LaunchedEffect(clicked.value) {
            if(clicked.value){
                scale.animateTo(5f, animationSpec = tween(800))
            }
            }
        Image(
            modifier = Modifier
                .size(165.563f.wp(),93.21614f.bhp())
                .offset(y = 342.24f.hp()),
            painter = painterResource(R.drawable.img_home_scale),
            contentDescription = "scale"
        )
        Image(
            modifier = Modifier
                .padding(top = 83f.hp())
                .size(211f.wp(),295f.bhp()),
            painter = painterResource(id = R.drawable.img_nyamee_normal),
            contentDescription = "homescale scale bg",
        )

        Box(
            modifier = Modifier.offset(
                y = 4f.hp(), x = 56f.wp()
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 103f.bhp(), width = 275f.wp()),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "조금 더 힘내면 원하는 몸무게에\n 금방 도달 할 수 있을 거야",
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                lineHeight = 20.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
                    .padding(bottom = 17f.bhp())
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
                        top = 458f.hp()
                    )
                    .clip(RoundedCornerShape(topStart = 60f.bhp(), topEnd = 60f.bhp()))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.25.dp,
                        Color(0xFF000000),
                        RoundedCornerShape(topStart = 60f.bhp(), topEnd = 60f.bhp())
                    )
                    .padding(horizontal = 25f.wp(), vertical = 25f.bhp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.padding(top = 8.dp, start = 31.dp, end = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_weight),
                        contentDescription = null,
                        modifier = Modifier
                            .width(300f.wp()),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = "<현재 체중>",
                        style = DungGeunMo24,
                        fontSize = 24f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 55f.bhp())
                    )
                    Text(
                        text = scaleText,
                        style = textSize,
                        fontSize = 35f.isp(),
                        color = Color(0xFF713E3A),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 25f.bhp())
                    )
                }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70f.bhp())
                        .clip(RoundedCornerShape(20f.bhp()))
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                        .clickable(
                            onClick = {
                                clicked.value = true
                                coroutineScope.launch {
                                    navController.navigate(Route.HomeScaleInput.route)
                                }
                            }
                        )
                ) {
                    Text(
                        text = buttonText,
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(vertical = 14.dp)
                            .align(Alignment.Center)
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