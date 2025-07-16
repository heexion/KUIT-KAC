package com.konkuk.kuit_kac.presentation.diet.screen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealRecordScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF3C1), Color.White)
                )
            )
    ) {
        // 1. 상단 바
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                // 뒤로가기 아이콘
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arow),
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )

                // 타이틀
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diet),
                        contentDescription = "식단 아이콘",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "식단",
                        style = DungGeunMo20,
                        color = Color(0xFF713E3A)
                    )
                }
            }

            // 구분선
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black)
            )
        }
        Spacer(modifier = Modifier.height(200.dp))
        // 콘텐츠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(162.dp))

            // 말풍선
            Box(
                modifier = Modifier
                    .width(272.dp)
                    .height(96.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    contentDescription = "말풍선",
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = "기록을 어떻게 할까?",
                    style = DungGeunMo20,
                    lineHeight = 28.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                )
            }
            // 캐릭터
            EllipseNyam(
                ellipseLength = 182.0,
                mascotLength = 109.0
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 버튼 1: 저장된 내 식단 선택
            Button(
                onClick = { navController.navigate(Route.DietExist.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.White, Color(0xFFFFE667))
                        )
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = "저장된 내 식단 선택하기",
                    style = DungGeunMo20,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 버튼 2: 직접 입력하기
            Button(
                onClick = { navController.navigate("meal_search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.White, Color(0xFFFFE667))
                        )
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "직접 입력하기",
                        style = DungGeunMo20,
                        color = Color.Black
                    )
                    Text(
                        text = "(외식 시 추천!)",
                        style = DungGeunMo15,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealRecordScreenPreview() {
    val navController = rememberNavController()
    MealRecordScreen(navController = navController)
}
