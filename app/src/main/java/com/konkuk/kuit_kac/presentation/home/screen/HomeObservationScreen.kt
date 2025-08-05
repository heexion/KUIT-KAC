package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.VerticalScrollbar
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.data.response.CoachReportResponseDto
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeObservationBox
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.viewmodel.CoachReportViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20





@Composable
fun HomeObservationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val lazyState = rememberLazyListState()
    val viewModel: CoachReportViewModel = hiltViewModel()
    val coachReport = viewModel.coachReport.value

    LaunchedEffect(Unit) {
        viewModel.loadCoachReport(userId = 1)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 477.73f.bhp())
        )
//        EllipseNyam(
//            modifier = Modifier
//                .padding(top = 122f.hp())
//                .align(Alignment.TopCenter),
//            mascotLength = 136.0, ellipseLength = 227.0
//        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            HamcoachGif(
                modifier = Modifier.offset(y = 90.18f.hp()),
                num = 1,
                ellipseLength = 227.0,
                mascotLength = 180.0,
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 30f.hp())
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 93f.bhp(), width = 248f.wp())
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "최근 아쉬웠던\n 부분들이야!",
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                lineHeight = 20f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20f.bhp())
                    .width(248f.wp())
                    .align(Alignment.TopCenter)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24f.wp())
                    .fillMaxHeight(0.6f)
                    .clip(shape = RoundedCornerShape(30f.bhp()))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        width = 1.25.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 30f.bhp())
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "<햄코치의 관찰일지>",
                    lineHeight = 28f.isp(),
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(vertical = 14.39f.bhp())
                )

                val observeList = remember(key1 = coachReport) {
                    mutableListOf<String>().apply {
                        coachReport?.let { report: CoachReportResponseDto ->
                            if (report.diningOutNum > 0) add("외식 ${report.diningOutNum}회")
                            if (report.fastingLevel == "LOW") add("공복 시간 적음")
                            if (report.drinkingLevel == "LOW") add("잦은 술자리")
                            if (report.deliveryLevel == "LOW") add("수시로 배달 어플")
                            if (report.lateNightLevel == "LOW") add("잦은 야식")
                        }
                    }
                }

                Box() {
                    LazyColumn(
                        state = lazyState,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            start = 32f.wp(),
                            end = 32f.wp(),
                            bottom = 70f.bhp()
                        )
                    ) {
                        items(observeList) { observe ->
                            HomeObservationBox(
                                value = observe,
                            )
                        }
                    }
//                    VerticalScrollbar(
//                        scrollState = lazyState,
//                        modifier = Modifier
//                            .align(Alignment.TopEnd)
//                            .fillMaxHeight(0.6f)
//                            .padding(end = 15f.wp(), top = 60f.bhp()),
//                        thumbColor = Color(0xFFFFE667)
//                    )
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun HomeObservationScreenPreview() {
    val navController = rememberNavController()
    HomeObservationScreen(
        navController = navController
    )
}

