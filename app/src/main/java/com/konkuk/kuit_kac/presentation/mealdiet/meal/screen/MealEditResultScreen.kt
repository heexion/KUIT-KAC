
package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun MealEditResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val bgColors = listOf(Color(0xFFFFFFFF), Color(0xFFFFE3B5))
    val shadow = Color(0xFFF1C67F)
    val messageText = "수정 완료!\n수고 많았어~!"
    val nyameeImg = R.drawable.img_nyamee_happy
    val hamCoachImg = R.drawable.img_hamcoach_normal

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(bgColors)
            ),
    ) {
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            lineHeight = 28f.isp(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.hp())
                .align(Alignment.TopCenter)
        )

        // 배경 조명
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 638.86f.hp(), start = 165f.wp())
                .size(height = 50f.bhp(), width = 181f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 648f.hp(), start = 39.68f.wp())
                .size(height = 33f.hp(), width = 67.6f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )
        EllipseNyam(
            mascotLength = 139.0,
            ellipseLength = 232.0,
            modifier = Modifier
                .offset(x=-20f.wp(), y = 255f.hp())
        )
        Image(
            modifier = Modifier
                .padding(top = 342.12f.hp(), start = 86.5f.wp())
                .size(338f.wp(),338f.bhp()),
            painter = painterResource(id = nyameeImg),
            contentDescription = null,
        )

        // 말풍선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 79f.hp(), start = 24f.wp()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(364f.wp(),206f.bhp()),
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
                modifier = Modifier.padding(bottom = 31.13.dp)
            )
        }

        // 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 24f.wp(), end = 24f.wp(), bottom = 25f.bhp()+
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                )
                .height(70f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                    )
                )
                .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                .clickable(
                    onClick = {
                        navController.navigate(Route.Home.route)
                    }
                ),
        ) {
            Text(
                text = "홈으로 돌아가기",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF000000),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealResultScreenPreview() {
    val navController = rememberNavController()
    MealEditResultScreen(navController = navController)
}
