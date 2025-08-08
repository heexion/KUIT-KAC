package com.konkuk.kuit_kac.presentation.login.screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun LoginEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val email: String = "user_id@naver.com"
    var showSheet by remember { mutableStateOf(false) }
    val sheetHeight = 452f.bhp()

    val offsetY by animateDpAsState(
        targetValue = if (showSheet) 0.dp else sheetHeight,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
        label = "sheetOffset"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 79f.hp()), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                modifier = Modifier.size(365.5f.wp(), 129f.bhp()),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                text = "이메일을 확인해줘!",
                style = DungGeunMo24,
                fontSize = 24f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24f.bhp())
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 24f.wp(), end = 24f.wp(), top = 240f.hp())
                .border(
                    width = 1.dp,
                    color = Color(0xFF000000),
                    shape = RoundedCornerShape(size = 18.dp)
                )
                .fillMaxWidth()
                .height(70f.bhp())
                .background(color = Color.Transparent, shape = RoundedCornerShape(size = 18.dp))
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16f.wp())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_login_kakao),
                    modifier = Modifier.size(38f.wp(), 38f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = email,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .padding(start = 16f.wp())
                        .align(Alignment.CenterVertically)
                )
            }
        }

        DefaultButton(
            onClick = {showSheet = true},
            value = "다음으로",
            buttonHeight = 70f,
            isOrange = true,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(), end = 24f.wp(),
                    bottom = 80f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                )
        )

        if (showSheet) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x90000000))
                    .clickable { showSheet = false } // 바깥 클릭 시 닫힘
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = offsetY),
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                        .height(452f.bhp()).fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                            )
                        )
                        .border(
                            1.25.dp,
                            Color.Black,
                            RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp())
                        )
                        .padding(horizontal = 25f.wp(), vertical = 25f.bhp())
                        .clickable(enabled = false) {},
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "약관에 동의해줘",
                        style = DungGeunMo24,
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(top = 7.89f.bhp())
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginEmailScreenPreview() {
    val navController = rememberNavController()
    LoginEmailScreen(navController = navController)
}