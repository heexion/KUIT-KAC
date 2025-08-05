package com.konkuk.kuit_kac.presentation.fitness.screen

import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessRoutineEditScreen(
    navController: NavHostController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    fitnessItems: List<FitnessData>
) {
    val Clicked = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 상단 배경
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .padding(start = 20f.wp(), end = 20f.wp(), top = 16f.hp(), bottom = 11f.bhp())
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fitness),
                            contentDescription = "운동 아이콘",
                            modifier = Modifier.size(20f.wp())
                        )
                        Spacer(modifier = Modifier.width(4f.wp()))
                        Text(
                            text = "운동",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF713E3A)
                        )
                    }

                    Spacer(modifier = Modifier.height(17.5f.bhp()))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "운동 기록",
                            isSelected = selectedTab == "기록",
                            buttonHeight = 49,
                            onClick = { onTabClick("기록") }
                        )
                        Spacer(modifier = Modifier.width(16f.wp()))
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "나만의 운동 루틴",
                            isSelected = selectedTab == "나만의",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate(route = Route.Fitness.route)
                                onTabClick("나만의")
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF000000))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFF3C1),
                                Color(0xFFFFFFFF),
                                Color(0xFFFFF3C1)
                            )
                        )
                    )
                    .padding(horizontal = 20f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(28f.bhp()))

                // 안내 문구 + 햄스터
                Image(
                    modifier = Modifier
                        // .offset(x = 70.31f.wp())
                        .size(323f.wp(),78f.bhp()),
                    painter = painterResource(R.drawable.img_fitnessroutine_text),
                    contentDescription = "textballoon"
                )
//                EllipseNyam(
//                   // modifier = Modifier.padding(top = 8f.bhp()),
//                    ellipseLength = 137.5,
//                    mascotLength = 82.3
//                )

                HamcoachGif(
                    num = 1,
                    ellipseLength = 137.5,
                    mascotLength = 116.0
                )
                //Spacer(modifier = Modifier.height(24f.bhp()))

                // 루틴 카드
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4f.wp())
                ) {
                    Image(
                        modifier = Modifier
                            .matchParentSize()
                            .graphicsLayer {
                                scaleX = 1.38f
                                scaleY = 1.0f
                            },
                        painter = painterResource(R.drawable.img_all_tilted_rectangle),
                        contentDescription = "tilted Rectangle"
                    )

                    FitnessCard(
                        navController = navController,
                        title = "하체루틴_허벅지..",
                        fitnessList = fitnessItems
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .offset(x = 182f.wp(), y = 25f.bhp())
                            .size(35f.bhp(), 35f.bhp())
                            .clip(RoundedCornerShape(11f.bhp()))
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                                )
                            )
                            .clickable(onClick = { Clicked.value = true })
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(11f.bhp())),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.svg_all_point),
                            contentDescription = "pointer",
                            modifier = Modifier.size(9f.wp(), 13f.bhp())
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28f.bhp()))

                // 하단 진행 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70f.bhp())
                        .clip(RoundedCornerShape(20f.wp()))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
                        .clickable { /* 진행 액션 */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "이 루틴을 진행했어!",
                        style = DungGeunMo20,
                        textAlign = TextAlign.Center,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000)
                    )
                }

                Spacer(modifier = Modifier.height(100f.hp()))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFitnessRoutineEditScreen() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("기록") }

    // 샘플 리스트 (삭제 이벤트는 Log로 처리)
    val sampleItems = remember {
        mutableStateListOf(
            FitnessData(
                id = 1,
                name = "레그 컬",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 컬 삭제됨") }
            ),
            FitnessData(
                id = 2,
                name = "레그 프레스",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 프레스 삭제됨") }
            ),
            FitnessData(
                id = 3,
                name = "레그 익스텐션",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 익스텐션 삭제됨") }
            )
        )
    }


    FitnessRoutineEditScreen(
        navController = navController,
        selectedTab = selectedTab,
        onTabClick = { selectedTab = it },
        fitnessItems = sampleItems
    )
}

