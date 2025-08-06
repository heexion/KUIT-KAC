
package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen


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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24


@Composable
fun MealTimeScreen(
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel()
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

        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(69f.hp()))

            // 말풍선
            Box(
                modifier = Modifier
                    .width(292f.wp())
                    .height(114f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .matchParentSize(),
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    contentDescription = "말풍선"
                )
                Text(
                    text = "자 마지막으로, 몇시에 먹었어?\n공복시간을 체크해줄게!",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 28f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 2f.bhp(), bottom = 20f.bhp())
                )
            }


            // 햄코치 캐릭터
            EllipseNyam(
                ellipseLength = 157.3,
                mascotLength = 94.206
            )

            Spacer(modifier = Modifier.height(44f.bhp()))

            // 시간 선택 박스
            Box(
                modifier = Modifier
                    .width(364f.wp())
                    .height(211f.bhp()),
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
                        .padding(horizontal = 24f.wp())
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
                                color = if (isAM) Color(0xFF000000) else Color(0xB8707070)
                            ),
                            fontSize = 17f.isp(),
                            modifier = Modifier.clickable { isAM = true }
                        )
                        Spacer(modifier = Modifier.height(12f.bhp()))
                        Text(
                            text = "오후",
                            style = DungGeunMo24.copy(
                                fontWeight = if (!isAM) FontWeight.Bold else FontWeight.Normal,
                                color = if (!isAM) Color(0xFF000000) else Color(0xB8707070)
                            ),
                            fontSize = 24f.isp(),
                            modifier = Modifier.clickable { isAM = false }
                        )
                    }

                    Spacer(modifier = Modifier.width(8f.wp()))

                    //구분선
                    Box(
                        modifier = Modifier
                            .width(2f.wp())
                            .height(173f.bhp())
                            .background(Color(0xFFFFE667))
                    )


                    Spacer(modifier = Modifier.width(12f.wp()))


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
                                    .height(120f.bhp())
                                    .weight(0.5f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(hours) { hour ->
                                    Text(
                                        text = hour,
                                        style = DungGeunMo24.copy(
                                            fontSize = 24f.isp(),
                                            color = if (hour == selectedHour) Color(0xFF000000) else Color(0xB8707070)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { selectedHour = hour }
                                            .padding(vertical = 4f.bhp()),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                            // :
                            Text(
                                text = ":",
                                style = DungGeunMo24.copy(
                                    fontSize = 24f.isp(),
                                    color = Color(0xFF000000)
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 4f.wp()),
                                textAlign = TextAlign.Center
                            )

                            // 분
                            LazyColumn(
                                modifier = Modifier
                                    .height(120f.bhp())
                                    .weight(0.5f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(minutes) { minute ->
                                    Text(
                                        text = minute,
                                        style = DungGeunMo24.copy(
                                            fontSize = 24f.isp(),
                                            color = if (minute == selectedMinute) Color(0xFF000000) else Color(0xB8707070)
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { selectedMinute = minute }
                                            .padding(vertical = 4f.bhp()),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(40f.bhp()))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20f.wp(), vertical = 20f.bhp()),
                onClick = {
                    mealViewModel.setMealTime(selectedHour, selectedMinute, isAM)
                    if (mealViewModel.selectType.value == "간식"){
                        mealViewModel.createSnack()
                    }
                    else{
                        mealViewModel.createMeal()
                    }
                    navController.navigate("time_input_result") },
                value = "기록하기",
                buttonHeight = 70f,
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
