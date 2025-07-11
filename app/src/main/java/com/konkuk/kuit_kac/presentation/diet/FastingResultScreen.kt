package com.konkuk.kuit_kac.presentation.diet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FastingResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val bgColors = listOf(Color(0xFFFFFFFF), Color(0xFFD6E6F5))
    val shadow = Color(0x85B6C9DC)
    val messageText = "아이고.. 바빴구나?\n아니면 일부러 굶은 거야?\n단식으로 기록할게\n다음 끼니는 꼭 챙겨먹자!"
    val nyameeImg = R.drawable.ic_nyamee_sad
    val hamCoachImg = R.drawable.ic_hamcoach_angry

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .background(brush = Brush.verticalGradient(bgColors))
    ) {
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            color = Color(0xFF713E3A),
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.TopCenter)
        )

        // 그림자 효과 (햄코치 주변 백라이트)
        Image(
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            contentDescription = null,
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 630.dp, start = 160.dp, end = 60.dp)
                .size(height = 50.dp, width = 180.dp),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(shadow)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            contentDescription = null,
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 615.dp, start = 45.dp, end = 200.dp)
                .size(height = 32.dp, width = 80.dp),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(shadow)
        )

        // 말풍선 + 텍스트
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 79.dp, start = 24.dp, end = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
            )
            Text(
                text = messageText,
                style = DungGeunMo20,
                lineHeight = 32.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 2.dp, bottom = 30.dp)
            )
        }

        // 햄스터 코치 백라이트 & 햄스터
        Image(
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 235.dp, end = 200.dp)
                .size(232.dp)
        )
        Image(
            painter = painterResource(id = hamCoachImg),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 277.dp, start = 26.dp, end = 240.dp)
                .size(139.dp)
        )

        // 냠이 캐릭터
        Image(
            painter = painterResource(id = nyameeImg),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 320.dp, start = 86.dp, end = 2.dp)
                .size(350.dp)
        )

        // 돌아가기 버튼
        Button(
            onClick = {
                navController.navigate(Route.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(25.dp)
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
                modifier = Modifier.padding(vertical = 14.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FastingResultPreview() {
    FastingResultScreen(navController = rememberNavController())
}
