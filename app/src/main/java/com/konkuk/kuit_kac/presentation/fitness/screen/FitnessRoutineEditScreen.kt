package com.konkuk.kuit_kac.presentation.fitness.screen

import android.util.Log
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow
import kotlinx.coroutines.launch

@Composable
fun FitnessRoutineEditScreen(
    navController: NavHostController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        routineViewModel.getRoutineTemplate(userId = 1)
    }
    val coroutineScope = rememberCoroutineScope()
    val routines = routineViewModel.getRoutineTemplateState.value.orEmpty()
    val pagerState = rememberPagerState { routines.size.coerceAtLeast(1) }
    val rotateDegree = 10f

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(165f.bhp())
                    .background(color = deepYellow)
                    .border(1.dp, Color(0xFF000000))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16f.hp())
                        .height(28.8584f.bhp()),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_fitness),
                        contentDescription = "운동 아이콘",
                        modifier = Modifier.size(20f.wp()),
                    )
                    Spacer(modifier = Modifier.size(5f.wp()))
                    Text(
                        text = "운동",
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 17f.bhp(),
                            start = 24f.wp(), end = 24f.wp()
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SelectButton2(
                        modifier = Modifier
                            .size(174f.wp(), 49f.bhp())
                            .noRippleClickable {
                                onTabClick("기록")
                            },
                        isSelected = selectedTab == "기록",
                        value = "운동 기록",
                    )
                    SelectButton2(
                        modifier = Modifier
                            .size(174f.wp(), 49f.bhp())
                            .noRippleClickable {
                                navController.navigate(route = Route.Fitness.route)
                                onTabClick("나만의")
                            },
                        isSelected = selectedTab == "나만의",
                        value = "나만의 운동 루틴",
                    )
                }
            }
            //                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_fitness),
//                            contentDescription = "운동 아이콘",
//                            modifier = Modifier.size(20f.wp())
//                        )
//                        Spacer(modifier = Modifier.width(4f.wp()))
//                        Text(
//                            text = "운동",
//                            style = DungGeunMo20,
//                            fontSize = 20f.isp(),
//                            color = Color(0xFF713E3A)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(17.5f.bhp()))
//
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        SelectButton(
//                            modifier = Modifier.weight(1f),
//                            value = "운동 기록",
//                            isSelected = selectedTab == "기록",
//                            buttonHeight = 49,
//                            onClick = { onTabClick("기록") }
//                        )
//                        Spacer(modifier = Modifier.width(16f.wp()))
//                        SelectButton(
//                            modifier = Modifier.weight(1f),
//                            value = "나만의 운동 루틴",
//                            isSelected = selectedTab == "나만의",
//                            buttonHeight = 49,
//                            onClick = {
//                                navController.navigate(route = Route.Fitness.route)
//                                onTabClick("나만의")
//                            }
//                        )
//                    }
//                }
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .background(Color(0xFF000000))
//            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFF3C1),
                                Color(0xFFFFFFFF),
                                Color(0xFFFFF3C1)
                            )
                        )
                    )
                    .padding(horizontal = 20f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(28f.bhp()))

                // 안내 문구 + 햄스터
                Image(
                    modifier = Modifier
                        .size(323f.wp(), 78f.bhp()),
                    painter = painterResource(R.drawable.img_fitnessroutine_text2),
                    contentDescription = "textballoon"
                )

                HamcoachGif(
                    num = 1,
                    ellipseLength = 137.5,
                    mascotLength = 116.0
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300f.bhp())
                        .padding(horizontal = 4f.wp())
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize(),
                        pageSpacing = 40f.bhp()
                    ) { page ->
                        if (routines.isNotEmpty()) {
                            val routine = routines[page]
                            val fitnessList = routine.routineExerciseProfiles.map { profile ->
                                Fitness(
                                    id = profile.exercise.id,
                                    name = profile.exercise.name,
                                    targetMuscleGroup = profile.exercise.targetMuscleGroup,
                                    metValue = profile.exercise.metValue.toDouble(),
                                    type = 0
                                )
                            }
                            val offset =
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            FitnessCard(
                                navController = navController,
                                title = routine.name,
                                fitnessList = fitnessList,
                                modifier = Modifier
                                    .graphicsLayer { rotationZ = -rotateDegree * offset }
                            )
                        } else {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("루틴이 없습니다", textAlign = TextAlign.Center)
                            }
                        }
                    }

                    if (pagerState.currentPage < routines.lastIndex) {
                        Box(
                            Modifier
                                .align(Alignment.CenterEnd)
                                .offset(x = 16.dp)
                                .size(35f.bhp())
                                .clip(RoundedCornerShape(11f.bhp()))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.White,
                                            Color(0xFFFFB638)
                                        )
                                    )
                                )
                                .noRippleClickable {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                }
                                .border(1.dp, Color.Black, RoundedCornerShape(11f.bhp())),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.svg_all_point),
                                contentDescription = "Next",
                                modifier = Modifier.size(9f.wp(), 13f.bhp())
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4f.bhp()))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70f.bhp())
                        .clip(RoundedCornerShape(20f.wp()))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
                        .noRippleClickable {
                            if (routines.isNotEmpty()) {
                                val current = routines[pagerState.currentPage]
                                val fitnessItems = current.routineExerciseProfiles.map { p ->
                                    Fitness(
                                        id = p.exercise.id,
                                        name = p.exercise.name,
                                        targetMuscleGroup = p.exercise.targetMuscleGroup,
                                        metValue = p.exercise.metValue.toDouble(),
                                        type = 0
                                    )
                                }
                                routineViewModel.setSelectedRoutines(fitnessItems)
                                routineViewModel.setName(current.name) //  first() 대신 current -> 루틴이 없을때 앱 크래시 안나도록 수정함
                            }
                            navController.navigate("FitnessDetailInput")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "이 루틴을 진행했어!",
                        style = DungGeunMo20,
                        textAlign = TextAlign.Center,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000)
                    )
                }


            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFitnessRoutineEditScreen() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("기록") }

    // 샘플 리스트 (삭제 이벤트는 Log로 처리)
    val sampleItems = remember {
        mutableStateListOf(
            FitnessData(
                id = 1,
                name = "레그 컬",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 컬 삭제됨") }
            ),
            FitnessData(
                id = 2,
                name = "레그 프레스",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 프레스 삭제됨") }
            ),
            FitnessData(
                id = 3,
                name = "레그 익스텐션",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 익스텐션 삭제됨") }
            )
        )
    }
}

