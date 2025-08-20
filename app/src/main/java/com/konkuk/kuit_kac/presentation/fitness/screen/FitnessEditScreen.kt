package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessItem
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessEditScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val selectedRoutines = routineViewModel.selectedRoutines

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(
                brush =
                    Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                        radius = 2000f
                    )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4f.hp())
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .offset(x = 70.31f.wp())
                    .size(272f.wp(), 96f.bhp()),
                painter = painterResource(R.drawable.img_fitnessedit_textballoon),
                contentDescription = "textballoon"
            )
//            EllipseNyam(
//                modifier = Modifier
//                    .offset(y = 80f.bhp(), x = 117f.wp()),
//                ellipseLength = 177.17578, mascotLength = 106.1115
//            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 80f.bhp()),
                    num = 1,
                    ellipseLength = 177.17578,
                    mascotLength = 145.0,
                )
            }


            Column(
                modifier = Modifier
                    .padding(top = 256f.bhp(), start = 24f.wp())
                    .width(364f.wp())
                    .clip(RoundedCornerShape(20f.bhp()))
                    .background(color = Color(0xFFFFF1AB))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22f.bhp(), start = 94f.wp(), end = 94f.wp())
                        .height(28f.bhp())
                        .clip(RoundedCornerShape(7f.wp()))
                        .background(color = Color(0xFFFFFCEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = routineViewModel.routineName.value ?: "운동 루틴",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 20f.bhp(), start = 16f.wp(), end = 15f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16f.bhp())
                ) {
                    selectedRoutines.forEach { item ->
                        FitnessItem(
                            FitnessNum = item?.id ?: 1,
                            image = R.drawable.ic_lowerbody,
                            FitnessName = item?.name ?: "",
                            isEditable = true,
                            onDeleteClick = {
                                routineViewModel.removeRoutine(item ?: Fitness(0, "", "", 1.0, 0))
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8f.bhp()))
                Row(
                    modifier = Modifier
                        .padding(start = 16f.wp(), end = 15f.wp(), top = 16f.bhp())
                        .fillMaxWidth()
                        .height(84f.bhp())
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFFFFFFFF))
                        .drawBehind {
                            val strokeWidth = 3.dp.toPx()
                            val pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                                floatArrayOf(
                                    4.dp.toPx(),
                                    4.dp.toPx()
                                ), 0f
                            )
                            val rect = Rect(0f, 0f, size.width, size.height)

                            drawRoundRect(
                                color = Color.Black,
                                style = Stroke(width = strokeWidth, pathEffect = pathEffect),
                                size = size,
                                cornerRadius = CornerRadius(15.dp.toPx())
                            )
                        }
                        .noRippleClickable(
                            onClick = {
                                navController.navigate(route = Route.FitnessSearch.route)
                            }
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7f.wp()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_diet_plus),
                            contentDescription = "add",
                            modifier = Modifier
                                .size(19f.wp(), 19f.bhp())
                        )
                        Text(
                            text = "운동 추가하기",
                            style = DungGeunMo15,
                            fontSize = 15f.isp(),
                            lineHeight = 20f.isp(),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(19f.bhp())
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32f.bhp(), start = 17f.wp(), end = 15f.wp())
                .height(70f.bhp())
                .clip(RoundedCornerShape(20f.wp()))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                    )
                )
                .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
                .noRippleClickable {
                    routineViewModel.setType("기록")
                    if (routineViewModel.routineId.value != null) {
                        routineViewModel.changeRoutine()
                    } else {
                        routineViewModel.createRoutine()
                    }
                    navController.navigate(Route.FitnessEditResult.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "저장하기",
                style = DungGeunMo20,
                textAlign = TextAlign.Center,
                fontSize = 20f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(115f.hp())
                .background(Color.Transparent)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun FitnessEditScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sampleList = remember {
        mutableStateListOf(
            FitnessData(1, "레그 컬", R.drawable.ic_lowerbody, {}),
            FitnessData(2, "레그 프레스", R.drawable.ic_lowerbody, {}),
            FitnessData(3, "레그 익스텐션", R.drawable.ic_lowerbody, {})
        )
    }

    FitnessEditScreen(
        navController = navController
    )
}