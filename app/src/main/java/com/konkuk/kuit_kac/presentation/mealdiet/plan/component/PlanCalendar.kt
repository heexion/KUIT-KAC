package com.konkuk.kuit_kac.presentation.mealdiet.plan.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.diet.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.mealdiet.plan.PlanTagType
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import java.time.LocalDate

@Composable
fun PlanCalendar(
    modifier: Modifier = Modifier,
    taggedDATES: Map<LocalDate, PlanTagType>,
    onNavigateToDetail: () -> Unit = {}
) {
    var currentMonth by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val daysOfWeek = listOf("일", "월", "화", "수", "목", "금", "토")
    var blueClicked = remember { mutableStateOf(false) }
    var pinkClicked = remember { mutableStateOf(false) }
    var taggedDates by remember { mutableStateOf<Map<LocalDate, PlanTagType>>(emptyMap()) }

    var breakfastClicked = remember { mutableStateOf(false) }
    var lunchClicked = remember { mutableStateOf(false) }
    var dinnerClicked = remember { mutableStateOf(false) }

    var isAddedOnce = remember { mutableStateOf(false) }

    LaunchedEffect(taggedDATES) {
        taggedDates = taggedDATES
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "날짜 선택하기",
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFF000000)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    selectedDate = null
                    pinkClicked.value = false
                    blueClicked.value = false
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plan_refresh),
                    modifier = Modifier
                        .size(20f.wp(), 20f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = "초기화",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )
            }
        }
        Spacer(modifier = Modifier.height(15f.bhp()))

        // 월 변경 헤더
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(40f.wp(), Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    modifier = Modifier.size(17f.wp(), 24f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
            }
            Text(
                text = "${currentMonth.monthValue}월",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF000000),
            )

            IconButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    modifier = Modifier.size(17f.wp(), 24f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.height(8f.bhp()))

        // 요일 헤더
        Row(modifier = Modifier.fillMaxWidth()) {
            var dayColor = 0xFF713E3A
            for (day in daysOfWeek) {
                if (day == "월")
                    dayColor = 0xFF000000
                Text(
                    text = day,
                    style = DungGeunMo17,
                    color = Color(dayColor),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(modifier = Modifier.padding(7.dp))

        // 날짜 그리드
        val firstDayOfMonth = currentMonth
        val lastDayOfMonth = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth())
        val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // 일요일이 0

        val totalDays = startDayOfWeek + lastDayOfMonth.dayOfMonth
        val weeks = (totalDays / 7) + if (totalDays % 7 != 0) 1 else 0
        Column {
            for (week in 0 until weeks) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (dayOfWeek in 0..6) {
                        val dayNumber = (week * 7 + dayOfWeek) - startDayOfWeek + 1
                        val isValid = dayNumber in 1..lastDayOfMonth.dayOfMonth

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1.2f)
                                .clickable(enabled = isValid) {
                                    val selected = currentMonth.withDayOfMonth(dayNumber)
                                    selectedDate = selected
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isValid) {
                                val thisDate = currentMonth.withDayOfMonth(dayNumber)
                                val isSelected = thisDate == selectedDate
                                val dayColor = when {
                                    dayOfWeek == 0 -> 0xFF713E3A
                                    else -> 0xFF000000
                                }

                                val tag = taggedDates.get(thisDate)
                                var color = when (tag) {
                                    PlanTagType.EATING_OUT -> Color(0xFF67D1FF)
                                    PlanTagType.DRINKING -> Color(0xFFFF7FD0)
                                    else -> Color.Transparent
                                }

                                Box(
                                    modifier = Modifier
                                        .size(39f.wp(), 39f.bhp())
                                        .background(
                                            color = color,
                                            shape = CircleShape
                                        )
                                )

                                if (isSelected) {
                                    if (blueClicked.value)
                                        Box(
                                            modifier = Modifier
                                                .size(39f.wp(), 39f.bhp())
                                                .background(
                                                    color = Color(0xFF67D1FF),
                                                    shape = CircleShape
                                                )
                                        )
                                    else if (pinkClicked.value)
                                        Box(
                                            modifier = Modifier
                                                .size(39f.wp(), 39f.bhp())
                                                .background(
                                                    color = Color(0xFFFF7FD0),
                                                    shape = CircleShape
                                                )
                                        )
                                    else {
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_plan_date_default_selected),
                                            modifier = Modifier.size(39f.wp(), 39f.bhp()),
                                            contentScale = ContentScale.FillBounds,
                                            contentDescription = null,
                                        )
                                    }
                                }
                                Text(
                                    text = "$dayNumber",
                                    style = DungGeunMo20,
                                    fontSize = 20f.isp(),
                                    color = Color(dayColor),
                                    modifier = Modifier.padding(6.dp)
                                )

                            } else {
                                Spacer(modifier = Modifier.height(0.dp))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(25f.bhp()))
        var confirmString = "다 입력했어!"

        // 외식/술자리 버튼
        if (!blueClicked.value && !pinkClicked.value)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12f.wp())
            ) {
                PlanSelectButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        if (selectedDate != null) {
                            blueClicked.value = true
                            pinkClicked.value = false
                        }
                    },
                    isBlue = true,
                    value = "외식",
                    height = 60f
                )
                PlanSelectButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        if (selectedDate != null) {
                            blueClicked.value = false
                            pinkClicked.value = true
                        }
                    },
                    isBlue = false,
                    value = "술자리",
                    height = 60f
                )
            }
        else
            Row(
                horizontalArrangement = Arrangement.spacedBy(18f.wp())
            ) {
                confirmString = "이때 먹을 예정이야!"
                PlanSelectButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        breakfastClicked.value = !breakfastClicked.value
                        lunchClicked.value = false
                        dinnerClicked.value = false
                    },
                    isSelected = breakfastClicked.value,
                    isBlue = blueClicked.value,
                    isSmall = true,
                    value = "아침",
                    height = 60f
                )

                PlanSelectButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        lunchClicked.value = !lunchClicked.value
                        breakfastClicked.value = false
                        dinnerClicked.value = false
                    },
                    isSelected = lunchClicked.value,
                    isBlue = blueClicked.value,
                    isSmall = true,
                    value = "점심",
                    height = 60f
                )

                PlanSelectButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        dinnerClicked.value = !dinnerClicked.value
                        lunchClicked.value = false
                        breakfastClicked.value = false
                    },
                    isSelected = dinnerClicked.value,
                    isBlue = blueClicked.value,
                    isSmall = true,
                    value = "저녁",
                    height = 60f
                )
            }

        PlanConfirmButton(
            modifier = Modifier.padding(top = 24f.bhp()),
            isAvailable = isAddedOnce.value || (selectedDate != null && (blueClicked.value || pinkClicked.value)),
            onClick = {
                if (confirmString == "다 입력했어!") {
                    onNavigateToDetail()
                }
                if (selectedDate != null) {
                    isAddedOnce.value = true
                    val updated = taggedDates.toMutableMap()
                    if (blueClicked.value) updated[selectedDate!!] = PlanTagType.EATING_OUT
                    else if (pinkClicked.value) updated[selectedDate!!] = PlanTagType.DRINKING
                    taggedDates = updated
                }
                pinkClicked.value = false
                blueClicked.value = false
            },
            value = confirmString,
            height = 65f
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PlanCalendarPreview() {
    val taggedDates = remember { mutableStateOf<Map<LocalDate, PlanTagType>>(emptyMap()) }
    PlanCalendar(
        taggedDATES = taggedDates.value
    )
}