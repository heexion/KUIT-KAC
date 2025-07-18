package com.konkuk.kuit_kac.presentation.fitness

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun FitnessEditResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val bgColors = listOf(Color(0xFFFFFFFF), Color(0xFFFFE3B5))
    val shadow = Color(0xFFF1C67F)
    val messageText = "'하체루틴_허벅지 위주'를\n성공적으로 저장했어!"
    val nyameeImg = R.drawable.ic_nyamee_happy
    val hamCoachImg = R.drawable.ic_hamcoach_normal

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .background(
                brush = Brush.verticalGradient(bgColors)
            ),
    ) {
        // 상단 타이틀
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            color = Color(0xFF713E3A),
            lineHeight = 28f.isp(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 30f.bhp())
                .align(Alignment.TopCenter)
        )

        // 배경 조명
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 630f.bhp(), start = 160f.wp(), end = 60f.wp())
                .size(width = 180f.wp(), height = 50f.bhp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 615f.bhp(), start = 45f.wp(), end = 200f.wp())
                .size(width = 80f.wp(), height = 32f.bhp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .padding(top = 235f.bhp(), end = 200f.wp())
                .size(232f.wp()),
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            contentDescription = null,
        )

        // 햄코치 & 냠미
        Image(
            modifier = Modifier
                .padding(top = 277f.bhp(), start = 26f.wp(), end = 240f.wp())
                .size(139f.wp()),
            painter = painterResource(id = hamCoachImg),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .padding(top = 320f.bhp(), start = 86f.wp(), end = 2f.wp())
                .size(350f.wp()),
            painter = painterResource(id = nyameeImg),
            contentDescription = null,
        )

        // 말풍선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 79f.bhp(), start = 24f.wp(), end = 24f.wp()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
            )
            Text(
                text = messageText,
                style = DungGeunMo24,
                lineHeight = 32f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 2f.bhp(), bottom = 30f.bhp())
            )
        }

        // 하단 버튼
        Button(
            onClick = {
                navController.navigate(Route.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(25f.bhp())
                .clip(RoundedCornerShape(20f.wp()))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                    )
                )
                .border(2.dp, Color.Black, RoundedCornerShape(20f.wp())),
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

@Preview
@Composable
private fun FitnessEditResultScreenPreview() {
    val navController = rememberNavController()
    FitnessEditResultScreen(navController=navController)
}