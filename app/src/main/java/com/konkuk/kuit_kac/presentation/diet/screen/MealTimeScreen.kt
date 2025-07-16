package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24


@Composable
fun MealTimeScreen(
    navController: NavController
) {
    val hours = (1..12).map { it.toString().padStart(2, '0') }
    val minutes = (0..59).map { it.toString().padStart(2, '0') }
    var isAM by remember { mutableStateOf(true) }
    var selectedHour by remember { mutableStateOf("09") }
    var selectedMinute by remember { mutableStateOf("00") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFF3C1), Color.White)))
    ) {
        // 상단 바
        Image(
            painter = painterResource(id = R.drawable.ic_back_arow),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .size(24.dp)
                .clickable { navController.popBackStack() }
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // 말풍선
            Box(
                modifier = Modifier
                    .width(310.dp)
                    .height(105.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    contentDescription = "말풍선",
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = "자 마지막으로, 몇시에 먹었어?\n공복시간을 체크해줄게!",
                    style = DungGeunMo17,
                    lineHeight = 28.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
                )
            }


            // 햄코치 캐릭터
            EllipseNyam(
                ellipseLength = 157.3,
                mascotLength = 94.206
            )

            Spacer(modifier = Modifier.height(44.dp))

            // 시간 선택 박스
            Box(
                modifier = Modifier
                    .width(364.dp)
                    .height(211.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_time),
                    contentDescription = "시간 선택 배경",
                    modifier = Modifier.matchParentSize()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {
                    // 오전/오후 선택 영역
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "오전",
                            style = DungGeunMo24.copy(
                                fontWeight = if (isAM) FontWeight.Bold else FontWeight.Normal,
                                color = if (isAM) Color.Black else Color.LightGray
                            ),
                            modifier = Modifier.clickable { isAM = true }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "오후",
                            style = DungGeunMo24.copy(
                                fontWeight = if (!isAM) FontWeight.Bold else FontWeight.Normal,
                                color = if (!isAM) Color.Black else Color.LightGray
                            ),
                            modifier = Modifier.clickable { isAM = false }
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //구분선
                    Box(
                        modifier = Modifier
                            .width(2.dp)
                            .height(173.dp)
                            .background(Color(0xFFFFE667))
                    )


                    Spacer(modifier = Modifier.width(12.dp))


// 시/분 선택 영역 -> 시랑 분 따로 리스트로 만들어서 선택하려면 : 이건 하나 있어야함.. 피그마 디자인과 다름
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // 시
                            LazyColumn(
                                modifier = Modifier
                                    .height(120.dp)
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(hours) { hour ->
                                    Text(
                                        text = hour,
                                        style = DungGeunMo24.copy(
                                            fontSize = 24.sp,
                                            color = if (hour == selectedHour) Color.Black else Color.LightGray
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { selectedHour = hour }
                                            .padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                            // :
                            Text(
                                text = ":",
                                style = DungGeunMo24.copy(
                                    fontSize = 24.sp,
                                    color = Color.Black
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 4.dp),
                                textAlign = TextAlign.Center
                            )

                            // 분
                            LazyColumn(
                                modifier = Modifier
                                    .height(120.dp)
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(minutes) { minute ->
                                    Text(
                                        text = minute,
                                        style = DungGeunMo24.copy(
                                            fontSize = 24.sp,
                                            color = if (minute == selectedMinute) Color.Black else Color.LightGray
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { selectedMinute = minute }
                                            .padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(40.dp))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                onClick = {navController.navigate("time_input_result") },
                value = "기록하기",
                buttonHeight = 70,
                isOrange = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealTimeScreenPreview() {
    val navController = rememberNavController()
    MealTimeScreen(navController = navController)
}
