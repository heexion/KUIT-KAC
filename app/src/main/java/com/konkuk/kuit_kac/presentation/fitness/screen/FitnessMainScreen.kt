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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow
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


    val infinitePageCount = Int.MAX_VALUE
    val actualPageCount = routines.size.coerceAtLeast(1)
    val initialPage = infinitePageCount / 2 - ((infinitePageCount / 2) % actualPageCount)

    val pageState = rememberPagerState(
        initialPage = initialPage - 1,
        pageCount = { infinitePageCount }
    )

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
                                navController.navigate("RecordEditGraph")
                                onTabClick("기록")
                            },
                        isSelected = selectedTab == "기록",
                        value = "운동 기록",
                    )
                    SelectButton2(
                        modifier = Modifier
                            .size(174f.wp(), 49f.bhp())
                            .noRippleClickable {
                                navController.navigate(Route.Fitness.route)
                                onTabClick("나만의")
                            },
                        value = "나만의 운동 루틴",
                        isSelected = true,
                    )

//                    SelectButton(
//                        modifier = Modifier.weight(1f),
//                        value = "운동 기록",
//                        isSelected = selectedTab == "기록",
//                        buttonHeight = 49,
//                        onClick = {
//                            navController.navigate("RecordEditGraph")
//                            onTabClick("기록")
//                        }
//                    )
//                    Spacer(Modifier.width(16f.wp()))
//                    SelectButton(
//                        modifier = Modifier.weight(1f),
//                        value = "나만의 운동 루틴",
//                        isSelected = selectedTab == "나만의",
//                        buttonHeight = 49,
//                        onClick = {
//                            navController.navigate(Route.Fitness.route)
//                            onTabClick("나만의")
//                        }
//                    )
                }
            }

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
                        .noRippleClickable { navController.navigate("RoutineGraph") }
                        .padding(horizontal = 16f.wp()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_diet_cross),
                        contentDescription = "아이콘",
                        modifier = Modifier.size(14.2f.wp(), 14.2f.bhp())
                    )
                    Spacer(Modifier.width(8f.wp()))
                    Text(
                        text = if (selectedTab == "기록") "운동 기록하기" else "나만의 운동 루틴 생성하기",
                        style = DungGeunMo20.copy(fontSize = 17f.isp()),
                        color = Color(0xFF000000)

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
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
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
//                                onClick = { navController.navigate(Route.FitnessExist.route) }    // 안 쓰이는 화면
                            )
                        }
                    }
                } else {
                    // Data state: show pager of templates
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                        Image(
//                            modifier = Modifier
//                                .matchParentSize()
//                                .graphicsLayer { scaleX = 1.38f },
//                            painter = painterResource(R.drawable.img_all_tilted_rectangle),
//                            contentDescription = null
//                        )
                        HorizontalPager(
                            state = pageState,
                            pageSpacing = 40f.wp(),
                            modifier = Modifier.fillMaxWidth()
                        ) { page ->
                            val realPage = page % actualPageCount
                            val template = routines[realPage]
                            val exercises = template.routineExerciseProfiles.map { profile ->
                                Fitness(
                                    id = profile.exercise.id,
                                    name = profile.exercise.name,
                                    targetMuscleGroup = profile.exercise.targetMuscleGroup,
                                    metValue = profile.exercise.metValue.toDouble(),
                                    type = 0
                                )
                            }
                            val pageOffset =
                                (pageState.currentPage - page) + pageState.currentPageOffsetFraction

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

                        // 왼쪽 버튼
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .offset(x = (-10f).wp(), y = 0f.bhp())
                                .size(35f.bhp(), 35f.bhp())
                                .clip(RoundedCornerShape(11f.bhp()))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.White, Color(0xFFFFB638))
                                    )
                                )
                                .noRippleClickable {
                                    coroutineScope.launch {
                                        val nextPage = (pageState.currentPage - 1)
                                        pageState.animateScrollToPage(nextPage)
                                    }
                                }
                                .border(1.dp, Color.Black, RoundedCornerShape(11f.bhp())),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.svg_all_point),
                                contentDescription = "pointer",
                                modifier = Modifier
                                    .size(9f.wp(), 13f.bhp())
                                    .graphicsLayer {
                                        scaleX = -1f // 좌우반전
                                    }
                            )
                        }
                        // 오른쪽 버튼
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = 180f.wp(), y = 0f.bhp())
                                .size(35f.bhp(), 35f.bhp())
                                .clip(RoundedCornerShape(11f.bhp()))
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color.White, Color(0xFFFFB638))
                                    )
                                )
                                .noRippleClickable {
                                    coroutineScope.launch {
                                        val nextPage = (pageState.currentPage + 1)
                                        pageState.animateScrollToPage(nextPage)
                                    }
                                }
                                .border(1.dp, Color.Black, RoundedCornerShape(11f.bhp())),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.svg_all_point),
                                contentDescription = "pointer",
                                modifier = Modifier.size(9f.wp(), 13f.bhp())
                            )
                        }

                    }

                    Spacer(Modifier.height(12f.bhp()))
                    // Pager indicators
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8f.bhp()),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        val currentPage = pageState.currentPage % routines.size
                        repeat(routines.size) { idx ->
                            val selected = currentPage == idx
                            Box(
                                Modifier
                                    .size(8f.bhp())
                                    .clip(CircleShape)
                                    .background(if (selected) Color.Black else Color(0xFFCCCCCC))
                            )
                        }
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
