
package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import kotlinx.coroutines.launch

@Composable
fun MealTimeScreen(
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel()
) {
    val amPmList = listOf("오전", "오후")
    val hoursList = (1..12).map { it.toString().padStart(2, '0') }
    val minutesList = (0..59).map { it.toString().padStart(2, '0') }

    var selectedAmPm by remember { mutableStateOf("오전") }
    var selectedHour by remember { mutableStateOf("09") }
    var selectedMinute by remember { mutableStateOf("00") }

    val coroutineScope = rememberCoroutineScope()

    // 오전/오후 (2개만, 자동 중앙 맞춤)
    val amPmState = rememberLazyListState(initialFirstVisibleItemIndex = amPmList.indexOf(selectedAmPm))
    LaunchedEffect(amPmState.isScrollInProgress) {
        if (!amPmState.isScrollInProgress) {
            val centerIndex = (amPmState.firstVisibleItemIndex + 0.5f).toInt()
                .coerceIn(0, amPmList.lastIndex)
            coroutineScope.launch {
                amPmState.animateScrollToItem(centerIndex)
            }
            selectedAmPm = amPmList[centerIndex]
        }
    }

    // 시/분 (무한 스크롤)
    val hourItems = List(1000) { hoursList[it % hoursList.size] }
    val minuteItems = List(1000) { minutesList[it % minutesList.size] }
    val hourState = rememberLazyListState(initialFirstVisibleItemIndex = 500 + hoursList.indexOf(selectedHour))
    val minuteState = rememberLazyListState(initialFirstVisibleItemIndex = 500 + minutesList.indexOf(selectedMinute))

    fun LazyListState.centerIndex(itemSize: Int): Int {
        return (firstVisibleItemIndex + 1).coerceIn(0, itemSize - 1)
    }

    LaunchedEffect(hourState.isScrollInProgress) {
        if (!hourState.isScrollInProgress) {
            selectedHour = hourItems[hourState.centerIndex(hourItems.size)]
        }
    }

    LaunchedEffect(minuteState.isScrollInProgress) {
        if (!minuteState.isScrollInProgress) {
            selectedMinute = minuteItems[minuteState.centerIndex(minuteItems.size)]
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFF3C1), Color(0xFFFFFFFF))))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(69f.hp()))

            // 말풍선
            Box(
                modifier = Modifier
                    .width(292f.wp())
                    .height(114f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )
                Text(
                    text = "자 마지막으로, 몇시에 먹었어?\n공복시간을 체크해줄게!",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 28f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-8).dp)
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                HamcoachGif(num = 1, ellipseLength = 157.3, mascotLength = 132.0)
            }

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
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24f.wp())
                ) {
                    // 오전/오후
                    LazyColumn(
                        state = amPmState,
                        modifier = Modifier
                            .height(120f.bhp())
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item { Spacer(modifier = Modifier.height(40f.bhp())) }
                        items(amPmList.size) { index ->
                            Box(
                                modifier = Modifier.height(40f.bhp()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = amPmList[index],
                                    style = DungGeunMo24.copy(
                                        fontSize = 24f.isp(),
                                        color = if (amPmList[index] == selectedAmPm) Color(0xFF000000) else Color(0xB8707070)
                                    )
                                )
                            }
                        }
                        item { Spacer(modifier = Modifier.height(40f.bhp())) }
                    }

                    Spacer(modifier = Modifier.width(8f.wp()))

                    Box(
                        modifier = Modifier
                            .width(2f.wp())
                            .height(173f.bhp())
                            .background(Color(0xFFFFE667))
                    )

                    Spacer(modifier = Modifier.width(12f.wp()))

                    // 시/분 + ":" 고정
                    Row(
                        modifier = Modifier.weight(2f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 시
                        LazyColumn(
                            state = hourState,
                            modifier = Modifier
                                .height(120f.bhp())
                                .weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item { Spacer(modifier = Modifier.height(40f.bhp())) }
                            items(hourItems.size) { index ->
                                Box(
                                    modifier = Modifier.height(40f.bhp()),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = hourItems[index],
                                        style = DungGeunMo24.copy(
                                            fontSize = 24f.isp(),
                                            color = if (hourItems[index] == selectedHour) Color(0xFF000000) else Color(0xB8707070)
                                        )
                                    )
                                }
                            }
                            item { Spacer(modifier = Modifier.height(40f.bhp())) }
                        }

                        // ":" 고정
                        Box(
                            modifier = Modifier
                                .height(40f.bhp())
                                .padding(horizontal = 4f.wp()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ":",
                                style = DungGeunMo24.copy(fontSize = 24f.isp(), color = Color(0xFF000000))
                            )
                        }

                        // 분
                        LazyColumn(
                            state = minuteState,
                            modifier = Modifier
                                .height(120f.bhp())
                                .weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item { Spacer(modifier = Modifier.height(40f.bhp())) }
                            items(minuteItems.size) { index ->
                                Box(
                                    modifier = Modifier.height(40f.bhp()),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = minuteItems[index],
                                        style = DungGeunMo24.copy(
                                            fontSize = 24f.isp(),
                                            color = if (minuteItems[index] == selectedMinute) Color(0xFF000000) else Color(0xB8707070)
                                        )
                                    )
                                }
                            }
                            item { Spacer(modifier = Modifier.height(40f.bhp())) }
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
                    mealViewModel.setMealTime(selectedHour, selectedMinute, selectedAmPm == "오전")
                    if (mealViewModel.selectType.value == "간식") {
                        mealViewModel.createSnack()
                    } else {
                        mealViewModel.createMeal()
                    }
                    navController.navigate("time_input_result")
                },
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
