package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetAddCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetCountCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetDistanceCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetWeightCard
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessRoutineDetailInputScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var selectedTab by remember { mutableStateOf("횟수") }

    val countCards = remember { mutableStateListOf("") }
    val weightCards = remember { mutableStateListOf("" to "") }
    val distanceCards = remember { mutableStateListOf("") }

    val scrollState = rememberScrollState()

    val selectedImageRes = when (selectedTab) {
        "횟수" -> R.drawable.ic_detailcount
        "무게" -> R.drawable.ic_detailweight
        "거리" -> R.drawable.ic_detaildistance
        else -> R.drawable.ic_detailcount
    }

    val isAllFilled = when (selectedTab) {
        "횟수" -> countCards.all { it.isNotBlank() && it.toIntOrNull() != null && it.toInt() > 0 }
        "무게" -> weightCards.all { (w, c) ->
            w.isNotBlank() && c.isNotBlank() &&
                    w.toFloatOrNull() != null && c.toIntOrNull() != null &&
                    w.toFloat() > 0f && c.toInt() > 0
        }
        "거리" -> distanceCards.all {
            it.isNotBlank() && it.toFloatOrNull() != null && it.toFloat() > 0f
        }
        else -> false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 117f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20f.hp())
            )

            Box(
                modifier = Modifier
                    .padding(top = 24f.bhp())
                    .width(240f.wp())
                    .height(50f.bhp())
            ) {
                Image(
                    painter = painterResource(id = selectedImageRes),
                    contentDescription = "선택된 탭 이미지",
                    modifier = Modifier.fillMaxSize()
                )

                Row(modifier = Modifier.fillMaxSize()) {
                    listOf("횟수", "무게", "거리").forEach { tab ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable { selectedTab = tab }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(85.2f.bhp()))

            when (selectedTab) {
                "횟수" -> {
                    countCards.forEachIndexed { index, count ->
                        FitnessSetCountCard(
                            setNumber = index + 1,
                            count = count,
                            onCountChange = { newText ->
                                if (newText.all { it.isDigit() }) {
                                    countCards[index] = newText
                                }
                            },
                            onDeleteClick = { countCards.removeAt(index) }
                        )
                        Spacer(modifier = Modifier.height(20f.bhp()))
                    }
                }

                "무게" -> {
                    weightCards.forEachIndexed { index, pair ->
                        FitnessSetWeightCard(
                            setNumber = index + 1,
                            weight = pair.first,
                            count = pair.second,
                            onWeightChange = { newWeight ->
                                if (newWeight.all { it.isDigit() || it == '.' }) {
                                    weightCards[index] = newWeight to pair.second
                                }
                            },
                            onCountChange = { newCount ->
                                if (newCount.all { it.isDigit() }) {
                                    weightCards[index] = pair.first to newCount
                                }
                            },
                            onDeleteClick = { weightCards.removeAt(index) }
                        )
                        Spacer(modifier = Modifier.height(20f.bhp()))
                    }
                }

                "거리" -> {
                    distanceCards.forEachIndexed { index, distance ->
                        FitnessSetDistanceCard(
                            setNumber = index + 1,
                            distance = distance,
                            onDistanceChange = { newText ->
                                if (newText.all { it.isDigit() || it == '.' }) {
                                    distanceCards[index] = newText
                                }
                            },
                            onDeleteClick = { distanceCards.removeAt(index) }
                        )
                        Spacer(modifier = Modifier.height(20f.bhp()))
                    }
                }
            }

            FitnessSetAddCard(
                onClick = {
                    when (selectedTab) {
                        "횟수" -> countCards.add("")
                        "무게" -> weightCards.add("" to "")
                        "거리" -> distanceCards.add("")
                    }
                }
            )

            Spacer(modifier = Modifier.height(32f.bhp()))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 117f.bhp())
        ) {
            PlanConfirmButton(
                modifier = Modifier.padding(horizontal = 24f.wp()),
                onClick = {
                    navController.navigate("fitness_detail_input")
                },
                isAvailable = isAllFilled,
                value = "입력하기"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFitnessRoutineDetailInputScreen() {
    FitnessRoutineDetailInputScreen(
        navController = rememberNavController()
    )
}
