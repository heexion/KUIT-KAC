package com.konkuk.kuit_kac.presentation.fitness.screen

import android.net.Uri
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.data.request.RoutineSetsDto
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.DetailRecordCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditFieldCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditIntensityCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessDetailInputScreen(
    modifier: Modifier = Modifier,
    routineViewModel: RoutineViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    val selected = routineViewModel.selectedRoutines.filterNotNull()

    val isAllFilled = selected.all { f ->
        val r = routineViewModel.getRecord(f.id)
        r.minutes > 0 && r.intensity >= 0
    }
    val totalKcal = routineViewModel.totalKcal()




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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 135.82f.hp()),
                    num = 1,
                    ellipseLength = 145.62891,
                    mascotLength = 110.0,
                )
            }
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
            Spacer(modifier = Modifier.height(300f.bhp()))

//            EllipseNyam(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 112.12f.hp()),
//                mascotLength = 87.70016,
//                ellipseLength = 145.62891
//            )
        }
        selected.forEach { item ->
            Spacer(modifier = Modifier.height(24f.bhp()))

            // name
            Box(
                modifier = Modifier
                    .padding(start = 24f.wp())
                    .width(154f.wp())
                    .height(40f.bhp())
                    .clip(RoundedCornerShape(15f.bhp()))
                    .background(
                        brush = Brush.verticalGradient(listOf(Color.White, Color(0xFFFFE667)))
                    )
                    .border(1.5.dp, Color.Black, RoundedCornerShape(20f.bhp())),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.name, style = DungGeunMo15, color = Color.Black)
            }

            val record = routineViewModel.getRecord(item.id)

            EditFieldCard(
                title = "운동 시간",
                value = record.minutes.toString(),
                onValueChange = { routineViewModel.updateMinutes(item.id, it) },
                unitLabel = "분"
            )

            EditIntensityCard(
                selectedIndex = record.intensity,
                onSelect = { routineViewModel.updateIntensity(item.id, it) }
            )

            DetailRecordCard(
                title = "상세 기록",
                lines = listOf("")
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
                routineViewModel.setType("기록")
                routineViewModel.createRoutine()
                navController.navigate(Route.FitnessEditResult.route)
            },
            isAvailable = isAllFilled,
            value = "기록하기")
        Spacer(modifier = Modifier
            .height(200f.bhp()))
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
}

private fun buildDetailLines(sets: List<RoutineSetsDto>): List<String> {
    return sets.mapIndexedNotNull { idx, s ->
        val prefix = "세트 ${idx + 1} "
        when {
            s.distance > 0 -> "$prefix${s.distance}km"
            s.weightKg > 0 && s.weightNum > 0 -> "$prefix${s.weightKg}kg x ${s.weightNum}회"
            s.count > 0 -> "$prefix${s.count}회"
            else -> null
        }
    }
}