package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.diet.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.fitness.component.EditFieldCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditIntensityCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessDetailInputScreen(
    modifier: Modifier = Modifier,
    fitnessList: List<FitnessData>
) {
    val scrollState = rememberScrollState()

    val timeInputs = remember { fitnessList.associate { it.id to mutableStateOf("0") } }
    val intensityInputs = remember { fitnessList.associate { it.id to mutableStateOf(-1) } }
    val detailInputs = remember { fitnessList.associate { it.id to mutableStateOf("") } }

    val isAllFilled = fitnessList.all { item ->
        val time = timeInputs[item.id]?.value?.toIntOrNull() ?: 0
        val intensity = intensityInputs[item.id]?.value ?: -1
        val detail = detailInputs[item.id]?.value ?: ""
        time > 0 && intensity >= 0 && detail.isNotBlank()
    }

    val totalKcal = fitnessList.sumOf { item ->
        val time = timeInputs[item.id]?.value?.toIntOrNull() ?: 0
        val factor = when (intensityInputs[item.id]?.value) {
            0 -> 3
            1 -> 5
            2 -> 8
            else -> 0
        }
        time * factor
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20f.hp())
                    .align(Alignment.TopCenter)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 59f.hp()), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    modifier = Modifier.size(332f.wp(), 103f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = "'하체 루틴_허벅지'\n위주로 했구나! 고생했어",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 22f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 28f.bhp())
                )
            }
            EllipseNyam(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 112.12f.hp()),
                mascotLength = 87.70016,
                ellipseLength = 145.62891
            )
        }

        fitnessList.forEach { item ->
            Spacer(modifier = Modifier.height(24f.bhp()))

            Box(
                modifier = Modifier
                    .padding(start = 24f.wp())
                    .width(154f.wp())
                    .height(40f.bhp())
                    .clip(RoundedCornerShape(15f.bhp()))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                        )
                    )
                    .border(1.5.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.name,
                    style = DungGeunMo15,
                    color = Color(0xFF000000)
                )
            }

            EditFieldCard(
                title = "운동 시간",
                value = timeInputs[item.id]?.value ?: "0",
                onValueChange = { timeInputs[item.id]?.value = it },
                unitLabel = "분"
            )

            EditIntensityCard(
                selectedIndex = intensityInputs[item.id]?.value ?: -1,
                onSelect = { intensityInputs[item.id]?.value = it }
            )

            DetailRecordCard(
                title = "상세 기록",
                value = detailInputs[item.id]?.value ?: "",
                onValueChange = { detailInputs[item.id]?.value = it }
            )
        }

        Spacer(modifier = Modifier.height(24.13f.bhp()))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5f.bhp())
                .padding(horizontal = 24f.wp())
                .background(Color(0xFF000000))
        )

        Spacer(modifier = Modifier.height(24f.bhp()))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24f.wp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_kcal_bar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(364f.wp())
                    .height(58.89f.bhp())
                    .clip(RoundedCornerShape(20f.bhp()))
                    .border(1.5.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
            )

            Row(
                modifier = Modifier
                    .width(364f.wp())
                    .height(58.89f.bhp())
                    .padding(horizontal = 16f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "최종 예상 소모 칼로리",
                    style = DungGeunMo17,
                    color = Color(0xFF000000)
                )

                Text(
                    text = "$totalKcal Kcal",
                    style = DungGeunMo20,
                    color = Color(0xFF713E3A)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.1f.bhp()))

        PlanConfirmButton(
            modifier = Modifier
                .padding(horizontal = 24f.wp()),
            onClick = {
                // TODO: 기록 저장 로직 작성
            },
            isAvailable = isAllFilled,
            value = "기록하기"
        )
    }
}


@Composable
fun DetailRecordCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24f.wp())
    ) {
        Row(
            modifier = Modifier
                .width(364f.wp())
                .height(59.9f.bhp())
                .padding(vertical = 8f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(Color(0xFFFFFFFF))
                .padding(horizontal = 12f.wp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17,
                color = Color(0xFF000000),
                modifier = Modifier.padding(start = 15.85f.wp())
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.14f.wp())
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = DungGeunMo15.copy(
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .widthIn(min = 40f.wp(), max = 100f.wp())
                        .padding(end = 4f.wp())
                        .background(Color(0xFFFFFFFF))
                )

                Text(
                    text = "ex) 운동 횟수, 거리",
                    style = DungGeunMo15,
                    color = Color(0xFF999999),
                    fontSize = 13f.isp(),
                    maxLines = 1
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewFitnessDetailInputScreen() {
    val sampleFitnessList = listOf(
        FitnessData(
            id = 1,
            name = "레그 컬",
            imageRes = R.drawable.ic_lowerbody,
            onDeleteClick = {}
        ),
        FitnessData(
            id = 2,
            name = "레그 프레스",
            imageRes = R.drawable.ic_lowerbody,
            onDeleteClick = {}
        ),
        FitnessData(
            id = 3,
            name = "레그 익스텐션",
            imageRes = R.drawable.ic_lowerbody,
            onDeleteClick = {}
        )
    )

    FitnessDetailInputScreen(fitnessList = sampleFitnessList)
}
