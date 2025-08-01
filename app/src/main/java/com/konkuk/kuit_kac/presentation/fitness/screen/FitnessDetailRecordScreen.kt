package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.DetailRecordCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditFieldCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditIntensityCard
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessDetailRecordScreen(
    name: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val particle = getObjectParticle(name)
    val message = "'$name'$particle 했구나!\n고생했어!"

    var time by remember { mutableStateOf("0") }
    var intensity by remember { mutableStateOf(-1) }
    var detail by remember { mutableStateOf("") }

    val isAllFilled = time.toIntOrNull()?.let { it > 0 } == true && intensity >= 0 && detail.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        // 상단 타이틀 + 말풍선
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20f.bhp())
                    .align(Alignment.TopCenter)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 59f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    modifier = Modifier.size(332f.wp(), 103f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = message,
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 22f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 28f.bhp())
                )
            }

            Spacer(modifier = Modifier.height(25.7f.bhp()))

            EllipseNyam(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 112.12f.bhp()),
                mascotLength = 87.70016,
                ellipseLength = 145.62891
            )
        }

        Spacer(modifier = Modifier.height(40f.bhp()))

        // 운동 이름 버튼
        Box(
            modifier = Modifier
                .padding(start = 24f.wp())
                .width(154f.wp())
                .height(40f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                    )
                )
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                style = DungGeunMo15,
                color = Color(0xFF000000)
            )
        }

        Spacer(modifier = Modifier.height(20f.bhp()))

        EditFieldCard(
            title = "운동 시간",
            value = time,
            onValueChange = { time = it },
            unitLabel = "분"
        )

        EditIntensityCard(
            selectedIndex = intensity,
            onSelect = { intensity = it }
        )

        DetailRecordCard(
            title = "상세 기록",
            value = detail,
            onValueChange = { detail = it }
        )

        Spacer(modifier = Modifier.height(97f.bhp()))

        PlanConfirmButton(
            modifier = Modifier.padding(horizontal = 24f.wp()),
            onClick = {
                // TODO: 실제 데이터 저장 로직이 있다면 먼저 실행한 후 이동
                navController.navigate("fitness_detail_add")
            },
            isAvailable = isAllFilled,
            value = "추가하기"
        )
    }
}


fun getObjectParticle(word: String): String {
    val lastChar = word.lastOrNull() ?: return "를"
    val unicode = lastChar.code
    val hasBatchim = (unicode - 0xAC00) % 28 != 0
    return if (hasBatchim) "을" else "를"
}

@Preview(showBackground = true)
@Composable
fun FitnessDetailRecordScreenPreview() {
    val navController = rememberNavController()
    FitnessDetailRecordScreen(
        name = "스쿼트",
        navController = navController
    )
}

