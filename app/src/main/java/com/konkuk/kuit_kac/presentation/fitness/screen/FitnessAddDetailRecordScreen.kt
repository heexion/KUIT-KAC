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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.data.request.RoutineSetsDto
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.DetailRecordCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditFieldCard
import com.konkuk.kuit_kac.presentation.fitness.component.EditIntensityCard
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessAddDetailRecordScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    name: String,
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val exercise = routineViewModel.selectedRoutines
        .filterNotNull()
        .firstOrNull { it.name == name }
    if (exercise == null) {
        navController.popBackStack()
        return
    }

    val particle = getObjectParticle(name)
    val message = "'$name'$particle 했구나! 고생했어!"

    val initial = routineViewModel.rGetRecord(name)
    var time by remember(name) {
        mutableStateOf(
            initial.minutes.takeIf { it > 0 }?.toString() ?: ""
        )
    }
    var intensity by remember(name) { mutableStateOf(initial.intensity) }
    var detail by remember(name) { mutableStateOf(initial.detail) }
    val detailLines = buildDetailLines(routineViewModel.setsByExerciseName[name].orEmpty())
    val hasDetail = detailLines.isNotEmpty() || detail.isNotBlank()
    val isAllFilled =
        time.toIntOrNull()?.let { it > 0 } == true &&
                intensity >= 0 &&
                hasDetail

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 147.82f.bhp()),
                    num = 1,
                    ellipseLength = 145.62891,
                    mascotLength = 110.0,
                )
            }
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
                    style = DungGeunMo15,
                    fontSize = 15f.isp(),
                    lineHeight = 20f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24f.bhp(), start = 50f.wp(), end = 50f.wp())
                )
            }

//            Spacer(modifier = Modifier.height(25.7f.bhp()))
            Spacer(modifier = Modifier.height(257f.bhp()))

//            EllipseNyam(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 112.12f.bhp()),
//                mascotLength = 87.70016,
//                ellipseLength = 145.62891
//            )
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
                fontSize = 10f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20f.bhp()))

        EditFieldCard(
            title = "운동 시간",
            value = time,
            onValueChange = { time = it
                routineViewModel.rUpdateMinutes(name, it)},
            unitLabel = "분"
        )

        EditIntensityCard(
            selectedIndex = intensity,
            onSelect = { intensity = it
                routineViewModel.rUpdateIntensity(name, it)}
        )

        DetailRecordCard(
            title = "상세 기록",
            lines =detailLines,
            onClick = {navController.navigate("FitnessRoutineDetailInput/${Uri.encode(name)}")}
        )

        Spacer(modifier = Modifier.height(97f.bhp()))

        PlanConfirmButton(
            modifier = Modifier.padding(horizontal = 24f.wp()),
            onClick = {
                routineViewModel.rUpdateMinutes(name, time)
                routineViewModel.rUpdateIntensity(name, intensity)
                routineViewModel.rUpdateDetail(name, detail)
                navController.navigate("FitnessAddRecordEdit")
            },
            isAvailable = isAllFilled,
            value = "추가하기"
        )
    }
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
