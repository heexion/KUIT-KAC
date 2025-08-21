package com.konkuk.kuit_kac.presentation.mealdiet.plan.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.plan.PlanTagType
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun PlanMonthScreen(
    navController: NavHostController,
    mealViewModel: MealViewModel,
    yearMonthStr: String
) {
    var ym by remember(yearMonthStr) { mutableStateOf(YearMonth.parse(yearMonthStr)) }
    val monthTags = mealViewModel.monthTags.value  // Map<LocalDate, Set<PlanTagType>>

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Header: month + nav
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ym.format(DateTimeFormatter.ofPattern("yyyy.MM")),
                style = DungGeunMo20,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF713E3A)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "이전",
                    style = DungGeunMo17,
                    modifier = Modifier.clickable {
                        ym = ym.minusMonths(1)
                        // keep VM data fresh when month changes
                        mealViewModel.getMonthPlan(yearMonth = ym.toString())
                    }
                )
                Text(
                    text = "다음",
                    style = DungGeunMo17,
                    modifier = Modifier.clickable {
                        ym = ym.plusMonths(1)
                        mealViewModel.getMonthPlan(yearMonth = ym.toString())
                    }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Weekday labels
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("일","월","화","수","목","금","토").forEach { d ->
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(text = d, style = DungGeunMo17, color = Color.Black)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // Build calendar cells (start from Sunday)
        val firstOfMonth = ym.atDay(1)
        val daysInMonth = ym.lengthOfMonth()
        val startOffset = ((firstOfMonth.dayOfWeek.value % 7)) // Sunday=0 ... Saturday=6
        val totalCells = startOffset + daysInMonth
        val rows = (totalCells + 6) / 7

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            repeat(rows) { row ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(7) { col ->
                        val cellIndex = row * 7 + col
                        val day = cellIndex - startOffset + 1
                        if (day in 1..daysInMonth) {
                            val date = ym.atDay(day)
                            val topTag = monthTags[date]?.firstOrNull() // your chooseTopTag result
                            DayCell(
                                date = date,
                                tagLabel = when (topTag) {
                                    PlanTagType.DRINKING -> "술"
                                    PlanTagType.EATING_OUT -> "외식"
                                    PlanTagType.AI_RECOMMEND -> "AI"
                                    else -> null
                                },
                                onClick = {
                                    navController.navigate("PlanIPGraph/day/${date}")
                                }
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    date: LocalDate,
    tagLabel: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(6.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            style = DungGeunMo17,
            color = Color.Black
        )
        if (tagLabel != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFFFEDD0))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(text = tagLabel, style = DungGeunMo17, color = Color(0xFF000000))
            }
        } else {
            Spacer(Modifier.height(16.dp))
        }
    }
}
