package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.OffsetEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessData
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
    fitnessData: List<FitnessData>

) {
    val sampleList = listOf(
        FitnessData(1, "레그 컬", R.drawable.ic_lowerbody, onDeleteClick = { }),
        FitnessData(2, "레그 프레스", R.drawable.ic_lowerbody, onDeleteClick = { }),
        FitnessData(3, "레그 익스텐션", R.drawable.ic_lowerbody, onDeleteClick = { })
    )
    val Clicked = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 상단 배경
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
                    // 타이틀
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fitness),
                            contentDescription = "운동 아이콘",
                            modifier = Modifier.size(20f.wp())
                        )
                        Spacer(modifier = Modifier.width(4f.wp()))
                        Text(
                            text = "운동",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF713E3A)
                        )
                    }

                    Spacer(modifier = Modifier.height(17.5f.bhp()))

                    // 탭 버튼
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "운동 기록",
                            isSelected = selectedTab == "기록",
                            buttonHeight = 49,
                            onClick = { onTabClick("기록") }
                        )
                        Spacer(modifier = Modifier.width(16f.wp()))
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "나만의 운동 루틴",
                            isSelected = selectedTab == "나만의",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate(route = Route.Fitness.route)
                                onTabClick("나만의")
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF000000))
            )

            // 메인 콘텐츠
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

                RecordFitnessButton(
                    onClick = { navController.navigate(route = Route.FitnessCreate.route) }
                )

                Spacer(modifier = Modifier.height(27f.bhp()))

                if (fitnessData.isEmpty()) {
                    // 운동이 없을 때
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
                            SpeechBubble(messageText = "현재 운동 루틴이\n비어있어요!")
                            EllipseNyam(
                                ellipseLength = 182.0, mascotLength = 109.0,
                                modifier = Modifier
                                    .clickable(
                                        onClick = {
                                            navController.navigate(Route.FitnessExist.route)
                                        }
                                    ))

                        }
                    }
                } else {
                    // 운동 루틴이 있는 경우 FitnessCard 출력
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4f.wp())
                    ) {
                        Image(
                            modifier = Modifier
                                .matchParentSize()
                                .graphicsLayer {
                                    scaleX = 1.38f
                                    scaleY = 1.0f
                                },
                            painter = painterResource(R.drawable.img_all_tilted_rectangle),
                            contentDescription = "tilted Rectangle"
                        )
                        SwipeCardPager(navController = navController)
                    }
                }
            }
        }
    }
}


@Composable
fun SpeechBubble(messageText: String) {
    Box(
        modifier = Modifier
            .width(219f.wp())
            .height(83f.bhp()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
            contentDescription = null,
            modifier = Modifier.matchParentSize()
        )
        Text(
            text = messageText,
            style = DungGeunMo20.copy(fontSize = 20f.isp()),
            lineHeight = 28f.isp(),
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 2f.bhp(), bottom = 20f.bhp())
        )
    }
}

@Composable
fun SwipeCardPager(navController: NavHostController) {
    val rotateDegree = 10F
    val sampleList = listOf(
        FitnessData(1, "레그 컬", R.drawable.ic_lowerbody, onDeleteClick = { }),
        FitnessData(2, "레그 프레스", R.drawable.ic_lowerbody, onDeleteClick = { }),
        FitnessData(3, "레그 익스텐션", R.drawable.ic_lowerbody, onDeleteClick = { })
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { sampleList.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 40.dp
        ) { page ->
            FitnessCard(
                navController = navController,
                title = "하체루틴_허벅지..",
                fitnessList = sampleList,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.offsetForPage(page)
                        rotationZ = -rotateDegree * pageOffset
                    }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(
                    x = 182f.wp(),
                    y = 25f.bhp()
                )
                .size(35f.bhp(), 35f.bhp())
                .clip(RoundedCornerShape(11f.bhp()))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                    )
                )
                .clickable(
                    onClick = {
                        coroutineScope.launch {
                            val nextPage =
                                (pagerState.currentPage + 1).coerceAtMost(sampleList.size - 1)
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }
                )
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(11f.bhp())),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.svg_all_point),
                contentDescription = "pointer",
                modifier = Modifier
                    .size(9f.wp(), 13f.bhp())
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

@Composable
fun RecordFitnessButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(364f.wp())
            .height(49f.bhp())
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(35f.wp())
            )
            .clickable { onClick() }
            .padding(horizontal = 16f.wp()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "운동 기록 아이콘",
            modifier = Modifier.size(27f.wp())
        )
        Spacer(modifier = Modifier.width(8f.wp()))
        Text(
            text = "나만의 운동 루틴 생성하기",
            style = DungGeunMo20.copy(fontSize = 17f.isp()),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessMainScreenPreview() {
    val navController = rememberNavController()

    val sampleFitnessData = listOf(
        FitnessData(
            id = 1,
            name = "레그 컬",
            imageRes = R.drawable.ic_lowerbody, // 실제 리소스 있어야 함
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

    FitnessMainScreen(
        navController = navController,
        selectedTab = "기록",
        onTabClick = {},
        onRecordClick = {},
        onFastedClick = {},
        fitnessData = sampleFitnessData
    )
}
