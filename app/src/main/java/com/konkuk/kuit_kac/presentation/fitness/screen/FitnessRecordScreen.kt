package com.konkuk.kuit_kac.presentation.fitness.screen

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

@Composable
fun FitnessRecordScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFFFF3C1), Color(0xFFFFFFFF))
                )
            )
    ) {
        // 1. 상단 바
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .height(67.59f.hp())
            ) {
                // 타이틀
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                        .padding(top = 20f.bhp())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_fitness),
                        contentDescription = "운동 아이콘",
                        modifier = Modifier.size(28.8f.wp(),28.8f.bhp())
                    )
                    Text(
                        text = "운동",
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF713E3A)
                    )
                }
            }

            // 구분선
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1f.bhp())
                    .background(Color(0xFF000000))
            )
        }
        // 콘텐츠
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(162f.hp()))

            // 말풍선
            Box(
                modifier = Modifier
                    .width(272f.wp())
                    .height(96f.bhp()),
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
                    fontSize = 20f.isp(),
                    lineHeight = 28f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 18f.bhp())
                )
            }
            // 캐릭터
            EllipseNyam(
                ellipseLength = 203.1,
                mascotLength = 121.6
            )

            Spacer(modifier = Modifier.height(7.81f.bhp()))

            // 버튼 1: 저장된 내 루틴 입력
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20f.bhp()))
                    .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                        )
                    )
                    .clickable(
                        onClick = {
                            navController.navigate(Route.FitnessRoutineEdit.route)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "저장된 내 루틴 입력",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }

            Spacer(modifier = Modifier.height(24f.bhp()))

            // 버튼 2: 기본 입력하기
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20f.bhp()))
                    .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                        )
                    )
                    .clickable(
                        onClick = { navController.navigate(Route.MealSearch.route) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                    Text(
                        text = "기본 입력",
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000)
                    )

            }
            Spacer(modifier = Modifier.height(24f.bhp()))

            // 버튼 3: 빠른 입력하기
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(RoundedCornerShape(20f.bhp()))
                    .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                        )
                    )
                    .clickable(
                        onClick = { navController.navigate(Route.MealSearch.route) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "빠른 입력",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFitnessRecordScreen() {
    val navController = rememberNavController()

    FitnessRecordScreen(navController = navController)
}
