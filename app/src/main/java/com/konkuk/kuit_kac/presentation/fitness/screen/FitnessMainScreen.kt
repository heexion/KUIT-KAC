package com.konkuk.kuit_kac.presentation.fitness.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessCard
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import kotlinx.coroutines.launch

@Composable
fun FitnessMainScreen(
    navController: NavHostController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    onRecordClick: () -> Unit,
    onFastedClick: () -> Unit,
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        routineViewModel.getRoutineTemplate(userId = 1)
    }

    // Fetch template routines
    val routines = routineViewModel.getRoutineTemplateState.value.orEmpty()
    val hasData = routines.isNotEmpty()
    val pagerState = rememberPagerState { routines.size.coerceAtLeast(1) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top header with tabs
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .padding(start = 20f.wp(), end = 20f.wp(), top = 16f.hp(), bottom = 11f.bhp())
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fitness),
                            contentDescription = "운동 아이콘",
                            modifier = Modifier.size(20f.wp())
                        )
                        Spacer(Modifier.width(4f.wp()))
                        Text(
                            text = "운동",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF713E3A)
                        )
                    }
                    Spacer(Modifier.height(17.5f.bhp()))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "운동 기록",
                            isSelected = selectedTab == "기록",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate("RecordEditGraph")
                                onTabClick("기록")
                            }
                        )
                        Spacer(Modifier.width(16f.wp()))
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "나만의 운동 루틴",
                            isSelected = selectedTab == "나만의",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate(Route.Fitness.route)
                                onTabClick("나만의")
                            }
                        )
                    }
                }
            }
            Divider(color = Color.Black, thickness = 1.dp)

            // Content area
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xFFFFF3C1), Color.White, Color(0xFFFFF3C1))
                        )
                    )
                    .padding(horizontal = 20f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(28f.bhp()))
                Row(
                    modifier = Modifier
                        .width(364f.wp())
                        .height(49f.bhp())
                        .border(1.dp, Color.Black, RoundedCornerShape(35f.wp()))
                        .clickable { navController.navigate("RoutineGraph") }
                        .padding(horizontal = 16f.wp()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "아이콘",
                        modifier = Modifier.size(27f.wp())
                    )
                    Spacer(Modifier.width(8f.wp()))
                    Text(
                        text = if (selectedTab == "기록") "운동 기록하기" else "나만의 운동 루틴 생성하기",
                        style = DungGeunMo20.copy(fontSize = 17f.isp())
                    )
                }

                Spacer(Modifier.height(27f.bhp()))

                if (!hasData) {
                    // Empty state
                    Box(
                        modifier = Modifier
                            .width(364f.wp())
                            .height(458f.bhp())
                            .clip(RoundedCornerShape(20f.bhp()))
                            .border(1.dp, Color.Black, RoundedCornerShape(20f.bhp()))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_meal_bg),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 88f.bhp()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = if (selectedTab == "기록") "현재 운동 기록이 비어있어요!" else "현재 루틴이 비어있어요!",
                                style = DungGeunMo20.copy(fontSize = 20f.isp()),
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(16f.bhp()))
                            HamcoachGif(
                                num = 1,
                                ellipseLength = 182.0,
                                mascotLength = 156.0,
                                onClick = { navController.navigate(Route.FitnessExist.route) }
                            )
                        }
                    }
                } else {
                    // Data state: show pager of templates
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            modifier = Modifier
                                .matchParentSize()
                                .graphicsLayer { scaleX = 1.38f },
                            painter = painterResource(R.drawable.img_all_tilted_rectangle),
                            contentDescription = null
                        )
                        HorizontalPager(
                            state = pagerState,
                            pageSpacing = 40f.wp(),
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            val template = routines[page]
                            val exercises = template.routineExerciseProfiles.map { profile ->
                                Fitness(
                                    id = profile.exercise.id,
                                    name = profile.exercise.name,
                                    targetMuscleGroup = profile.exercise.targetMuscleGroup,
                                    metValue = profile.exercise.metValue.toDouble(),
                                    type = 0
                                )
                            }
                            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            FitnessCard(
                                navController = navController,
                                title = template.name,
                                fitnessList = exercises,
                                modifier = Modifier.graphicsLayer { rotationZ = -10f * pageOffset },
                                onEditClick = {
                                    val encodedName = Uri.encode(template.name)
                                    navController.navigate(
                                        "RoutineEditGraph/RoutineEdit?routineId=${template.id}&name=${encodedName}"
                                    )
                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(12f.bhp()))
                    // Pager indicators
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8f.bhp()),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        repeat(routines.size) { idx ->
                            val selected = pagerState.currentPage == idx
                            Box(
                                Modifier
                                    .size(8f.bhp())
                                    .clip(CircleShape)
                                    .background(if (selected) Color.Black else Color(0xFFCCCCCC))
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .offset(x = (-20).dp, y = (-40).dp)
                            .size(35f.bhp())
                            .clip(RoundedCornerShape(11f.bhp()))
                            .background(
                                brush = Brush.verticalGradient(listOf(Color.White, Color(0xFFFFB638)))
                            )
                            .clickable {
                                coroutineScope.launch {
                                    val next = (pagerState.currentPage + 1).coerceAtMost(routines.lastIndex)
                                    pagerState.animateScrollToPage(next)
                                }
                            }
                            .border(1.dp, Color.Black, RoundedCornerShape(11f.bhp())),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.svg_all_point),
                            contentDescription = null,
                            modifier = Modifier.size(9f.wp(), 13f.bhp())
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessMainScreenPreview() {
    val navController = rememberNavController()
    FitnessMainScreen(
        navController = navController,
        selectedTab = "기록",
        onTabClick = {},
        onRecordClick = {},
        onFastedClick = {}
    )
}
