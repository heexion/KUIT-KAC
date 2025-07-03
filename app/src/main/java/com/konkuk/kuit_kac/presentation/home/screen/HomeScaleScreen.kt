package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo27
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35

@Composable
fun HomeScaleScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    weight: Double = 0.0
) {
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
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            painter = painterResource(id = R.drawable.bg_home),
            contentDescription = "homescale home bg",
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .padding(top = 83.dp)
                .size(290.dp),
            painter = painterResource(id = R.drawable.ic_nyamee_normal),
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
                    .padding(end = 18.dp, top = 10.dp),
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
                    .padding(top = 32.dp, start = 10.dp, end = 26.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .imePadding()
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
                        navController.navigate(Route.HomeScaleInput.route)
                    },
                    modifier = modifier
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
    HomeScaleScreen(
        modifier = Modifier,
        navController = TODO(),
        weight =2.4
    )
}