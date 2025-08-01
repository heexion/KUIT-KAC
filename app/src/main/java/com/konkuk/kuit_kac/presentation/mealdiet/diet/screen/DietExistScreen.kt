package com.konkuk.kuit_kac.presentation.mealdiet.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.screen.offsetForPage
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.DietCard
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow
import kotlinx.coroutines.launch

@Composable
fun DietExistScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineList: List<String> = listOf("아침", "점심", "저녁")
) {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val lazyState = rememberLazyListState()
    val existList = listOf(
        1, 2, 3, 4
    )
    val cal = 677;
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
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
                    .padding(top = 16f.hp()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(28.8584f.wp(), 28.8584f.bhp()),
                    painter = painterResource(R.drawable.ic_alcohol),
                    contentDescription = "utensils"
                )
                Text(
                    text = "식단",
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
                        .clickable {
                            navController.navigate(Route.DietCreate.route)
                        },
                    value = "식단기록"
                )
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp()),
                    value = "나만의 식단"
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush =
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                            radius = 2000f
                        )
                ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28f.bhp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(364f.wp(), 49f.bhp())
                        .clip(RoundedCornerShape(24f.bhp()))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(24f.bhp())),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_diet_cross),
                            contentDescription = "plus",
                            modifier = Modifier
                                .size(14.20361f.wp(), 14.20361f.bhp())
                        )
                        Text(
                            text = "나만의 식단 생성하기",
                            style = DungGeunMo17,
                            fontSize = 17f.isp(),
                            color = Color(0xFF000000)
                        )
                    }
                }
            }

            DietSwipeCardPager(
                navController = navController,
                modifier = Modifier.padding(top = 28f.bhp(), start = 24f.wp())
            )
            Spacer(
                modifier = Modifier.size(
                    170f.bhp() - WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                ),
            )
        }
    }
}

@Composable
fun DietSwipeCardPager(navController: NavHostController, modifier: Modifier = Modifier) {
    val rotateDegree = 10F
    val sampleList = listOf(
        1, 2, 3, 4
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { sampleList.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 40.dp
        ) { page ->
            DietCard(
                navController = navController,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = pagerState.offsetForPage(page)
                        rotationZ = -rotateDegree * pageOffset
                    }
            )
        }
        //오른쪽 버튼
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(
                    x = 170f.wp(),
                    y = 0f.bhp()
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

@Preview(showBackground = true)
@Composable
fun DietExistScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    DietExistScreen(navController = navController)
}

