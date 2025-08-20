package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun PlanDietMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.hp())
                .align(Alignment.TopCenter)
        )

//        EllipseNyam(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .padding(top = 194.22f.bhp()),
//            mascotLength = 127.45568,
//            ellipseLength = 212.81445
//        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            HamcoachGif(
                modifier = Modifier.offset(y = 214.22f.bhp()),
                num = 1,
                ellipseLength = 212.81445,
                mascotLength = 175.0,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 57.5f.hp()),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                modifier = Modifier.size(332f.wp(), 167f.bhp()),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                text = "식단 계획하러 왔구나!\n어떤 방식으로 식단을 짜볼까?",
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                lineHeight = 22f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40f.bhp())
            )
        }

        Column(
            modifier = Modifier.padding(
                start = 24.49f.wp(),
                end = 24.49f.wp(),
                top = 427.04f.hp()
            ),
            verticalArrangement = Arrangement.spacedBy(19.96f.bhp())
        ) {
            DefaultButton(
                onClick = {
                    navController.navigate(Route.PlanAI.route)
                },
                value = "외식/술자리를 고려한 AI 식단 추천",
                buttonHeight = 70f,
                isOrange = false
            )
            DefaultButton(
                onClick = {
                    navController.navigate(Route.PlanInPerson.route)
                },
                value = "직접 식단짜기",
                buttonHeight = 70f,
                isOrange = false
            )
            DefaultButton(
                onClick = {
                    navController.navigate("PlanCheckGraph")
                },
                value = "내 식단 계획 확인하기",
                buttonHeight = 70f,
                isOrange = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanDietMainScreenPreview() {
    val navController = rememberNavController()
    PlanDietMainScreen(
        navController = navController
    )
}