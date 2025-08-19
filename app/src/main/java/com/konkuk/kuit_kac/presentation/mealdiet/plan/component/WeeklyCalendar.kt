package com.konkuk.kuit_kac.presentation.mealdiet.plan.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun WeeklyCalendar(
    date: LocalDate = LocalDate.now(),
    onDateClick: (LocalDate) -> Unit = {} // 클릭된 날짜를 반환
) {
    var selectedDate by remember { mutableStateOf(date) }

    val monday = date.with(DayOfWeek.MONDAY)
    val weekDates = remember(date) {
        List(7) { i -> monday.plusDays(i.toLong()) }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        weekDates.forEach { day ->
            DayItem(
                date = day,
                isSelected = day == selectedDate,
                onClick = { clickedDate ->
                    selectedDate = clickedDate
                    onDateClick(clickedDate)
                }
            )

        }
    }
}


@Composable
private fun DayItem(date: LocalDate, isSelected: Boolean, onClick: (LocalDate) -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFF000000) else Color.Transparent
    val textColor = if (isSelected) Color(0xFFFFFFFF) else Color(0XFF000000)
    Box(
        modifier = Modifier
            .size(35.dp)
            .padding(vertical = 6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = textColor,
            style = DungGeunMo17,
            fontSize = 17F.isp(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyCalendarPreview() {
    WeeklyCalendar(LocalDate.of(2025, 8, 20))
}