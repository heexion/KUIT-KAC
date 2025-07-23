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
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import java.time.LocalDate

@Composable
fun PlanCalendar(
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    var currentMonth by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val daysOfWeek = listOf("일", "월", "화", "수", "목", "금", "토")

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
                                    onDateSelected(selected)
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

                                if (isSelected)
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_plan_date_default_selected),
                                        modifier = Modifier.size(39f.wp(), 39f.bhp()),
                                        contentScale = ContentScale.FillBounds,
                                        contentDescription = null,
                                    )

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
    }
}


@Preview(showBackground = true)
@Composable
private fun PlanCalendarPreview() {
    PlanCalendar()
}