package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.presentation.fitness.component.EditFieldCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditIntensityCard
import com.konkuk.kuit_kac.presentation.fitness.component.ReadOnlyFieldCard
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20


@Composable
fun FitnessFastInputScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val scrollState = rememberScrollState()
    var anaerobicTimeText by remember { mutableStateOf("0") }
    var selectedIntensity by remember { mutableStateOf(-1) }

    var aerobicTimeText by remember { mutableStateOf("0") }
    var selectedAerobicIntensity by remember { mutableStateOf(-1) }


    //칼로리 계산 예시
    // 무산소
    val anaerobicTime = anaerobicTimeText.toIntOrNull() ?: 0
    val anaerobicFactor = when (selectedIntensity) {
        0 -> 3 // easy
        1 -> 5 // normal
        2 -> 8 // hard
        else -> 0
    }
    val anaerobicKcal = anaerobicTime * anaerobicFactor

// 유산소
    val aerobicTime = aerobicTimeText.toIntOrNull() ?: 0
    val aerobicFactor = when (selectedAerobicIntensity) {
        0 -> 2 // easy
        1 -> 4 // normal
        2 -> 6 // hard
        else -> 0
    }
    val aerobicKcal = aerobicTime * aerobicFactor


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


        }
        Spacer(modifier = Modifier.height(32.dp))
        //무산소
        // 운동 이름 버튼
        Box(
            modifier = Modifier
                .padding(start = 24.dp)
                .width(154.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFFFE667)
                        )
                    )
                )
                .border(1.5.dp, Color.Black, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "무산소 운동",
                style = DungGeunMo15,
                color = Color.Black
            )
        }
        EditFieldCard(
            title = "운동 시간",
            value = anaerobicTimeText,
            onValueChange = {
                anaerobicTimeText = it.filter { ch -> ch.isDigit() }
            },
            unitLabel = "분"
        )

        //Spacer(modifier = Modifier.height(8.dp))

        EditIntensityCard(
            selectedIndex = selectedIntensity,
            onSelect = { selectedIntensity = it }
        )

        ReadOnlyFieldCard(
            title = "예상 소모 칼로리",
            value = anaerobicKcal.toString(),
            unitLabel = "kcal"
        )

        Spacer(modifier = Modifier.height(28.dp))

        // 유산소 운동
        Box(
            modifier = Modifier
                .padding(start = 24.dp)
                .width(154.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                    )
                )
                .border(1.5.dp, Color.Black, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "유산소 운동",
                style = DungGeunMo15,
                color = Color.Black
            )
        }

        EditFieldCard(
            title = "운동 시간",
            value = aerobicTimeText,
            onValueChange = {
                aerobicTimeText = it.filter { ch -> ch.isDigit() }
            },
            unitLabel = "분"
        )

        EditIntensityCard(
            selectedIndex = selectedAerobicIntensity,
            onSelect = { selectedAerobicIntensity = it }
        )

        ReadOnlyFieldCard(
            title = "예상 소모 칼로리",
            value = aerobicKcal.toString(),
            unitLabel = "kcal"
        )


        Spacer(modifier = Modifier.height(24.13.dp))

        //구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .padding(horizontal = 24.dp)
                .background(Color(0xFF000000))
        )

        Spacer(modifier = Modifier.height(24.dp))

        //최종 예상 소모 칼로리
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            // 이미지 배경 레이어
            Image(
                painter = painterResource(id = R.drawable.img_kcal_bar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(364.dp)
                    .height(58.89.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.5.dp, Color(0xFF000000), RoundedCornerShape(20.dp))
            )

            // 텍스트 오버레이
            Row(
                modifier = Modifier
                    .width(364.dp)
                    .height(58.89.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "최종 예상 소모 칼로리",
                    style = DungGeunMo17,
                    color = Color(0xFF000000)
                )

                Text(
                    text = "${anaerobicKcal + aerobicKcal} Kcal",
                    style = DungGeunMo20,
                    color = Color(0xFF713E3A)
                )
            }
        }
        Spacer(modifier = Modifier.height(32.1.dp))

        RecordButton(
            value = "기록하기",
            onClick = { /* TODO */ }
        )




    }
}


@Composable
fun RecordButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    value: String,
    height: Float = 60f
) {
    val image = R.drawable.bg_plan_confirm_button_selected

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(height.bhp())
            .clickable { onClick() }
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(image),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewFitnessFastInputScreen() {
    val navController = rememberNavController()
    FitnessFastInputScreen(navController = navController)
}
