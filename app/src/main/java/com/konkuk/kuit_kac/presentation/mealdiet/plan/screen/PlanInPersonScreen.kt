package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.plan.PlanTagType
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanCalendar
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import java.time.LocalDate

//직접 식단짜기 화면

@Composable
fun PlanInPersonScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var taggedDates by remember { mutableStateOf<Map<LocalDate, Set<PlanTagType>>>(emptyMap()) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

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

        EllipseNyam(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 194.22f.bhp()),
            mascotLength = 127.45568,
            ellipseLength = 212.81445
        )

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
                text = "예정된 날짜에 입력해줘!",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                lineHeight = 28f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40f.bhp())
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 23.91f.wp())
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        1.25.dp,
                        Color.Black,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Column(
                    modifier = Modifier.padding(horizontal = 16f.wp(), vertical = 22.32f.bhp()),
                ) {
                    PlanCalendar(
                        modifier = Modifier.padding(),
                        taggedDATES = taggedDates,
                        onDateSelected = { date ->
                            selectedDate = date
                        }
                    )
                    PlanConfirmButton(
                        modifier = Modifier.padding(top = 36f.bhp()),
                        isAvailable = if (selectedDate != null) true
                        else false,
                        onClick = {
                            if (selectedDate != null)
                                navController.navigate("plan_in_person_add")
                        },
                        value = "계획하기",
                        height = 65f
                    )
                    Spacer(
                        modifier = Modifier.size(
                            140f.bhp() - WindowInsets.navigationBars.asPaddingValues()
                                .calculateBottomPadding()
                        ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanInPersonScreenPreview() {
    val navController = rememberNavController()
    PlanInPersonScreen(
        navController = navController
    )
}