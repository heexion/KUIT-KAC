package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.home.component.NyameeGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun HomeResultScreen(
    modifier: Modifier = Modifier,
    value: Int,
    isSucceeded: Boolean,
    navController: NavHostController
) {
    var bgColors = listOf(Color(0xFFFFFFFF), Color(0xFFFFE3B5))
    var shadow = Color(0xFFF1C67F)
    var messageText = "체중이 ${value}kg 줄었네!\n수고 많았어!"
    var nyameeImg = R.drawable.img_nyamee_happy
    var hamCoachImg = R.drawable.img_hamcoach_normal


    // 체중이 늘었을 때(감량 실패 시) 값을 바꿔서 넣기
    if (!isSucceeded) {
        bgColors = listOf(Color(0xFFFFFFFF), Color(0xFFd6e6f5))
        shadow = Color(0x85B6C9DC)
        messageText = "체중이 ${value}kg 늘었네..\n더 열심히 해보자!"
        nyameeImg = R.drawable.img_nyamee_sad
        hamCoachImg = R.drawable.img_hamcoach_angry
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .background(
                brush = Brush.verticalGradient(bgColors)
            ),
    ) {
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            color = Color(0xFF713E3A),
            lineHeight = 28f.isp(),
            fontSize = 20f.isp(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.hp())
                .align(Alignment.TopCenter)
        )

        // 배경 조명
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 638.86f.hp(), start = 165f.wp(), end = 65.89f.wp())
                .size(height = 50f.bhp(), width = 180f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 650.99f.hp(), start = 44.69f.wp(), end = 284.79f.wp())
                .size(height = 33f.bhp(), width = 67f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )

//        Image(
//            modifier = Modifier
//                .padding(top = 255f.hp(), end = 200f.wp())
//                .size(232f.wp(), 232f.bhp()),
//            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
//            contentDescription = null,
//        )
//        Image(
//            modifier = Modifier
//                .padding(top = 297f.hp(), start = 26f.wp(), end = 240f.wp())
//                .size(139f.wp(), 139f.bhp()),
//            painter = painterResource(id = hamCoachImg),
//            contentDescription = null,
//        )
//        Image(
//            modifier = Modifier
//                .padding(top = 342.12f.hp(), start = 86.5f.wp(), end = 2f.wp())
//                .size(338.09961f.wp(), 338.09961f.bhp()),
//            painter = painterResource(id = nyameeImg),
//            contentDescription = null,
//        )

        HamcoachGif(
            modifier = Modifier.offset(x = (-30f).wp(), y = 255f.hp()),
            num = 2,
            ellipseLength = 222.0,
            mascotLength = 200.0
        )

        NyameeGif(
            modifier = Modifier.offset(x = 50f.wp(), y = 300f.bhp()),
            num = 5,
            sizePercent = 1.35f
        )

        // 말풍선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 79f.hp(), start = 24f.wp(), end = 24f.wp()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
            )
            Text(
                text = messageText,
                style = DungGeunMo24,
                fontSize = 24f.isp(),
                lineHeight = 32f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 30f.bhp())
            )
        }

        // 버튼
        Button(
            onClick = {
                navController.navigate(Route.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 24f.wp(), end = 24f.wp(),
                    bottom = 25f.bhp()+ WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                    )
                )
                .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        ) {
            Text(
                text = "홈으로 돌아가기",
                style = DungGeunMo20,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 14f.bhp())
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomeResultScreenPreview() {
    val navController = rememberNavController()
    HomeResultScreen(
        value = 1, isSucceeded = true,
        navController = navController
    )
}