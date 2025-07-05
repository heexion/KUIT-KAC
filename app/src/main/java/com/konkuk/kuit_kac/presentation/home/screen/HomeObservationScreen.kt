package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.home.component.ObservationBox
import com.konkuk.kuit_kac.presentation.home.component.VerticalScrollbar
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeObservationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val lazyState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            painter = painterResource(id = R.drawable.bg_whole_home),
            contentDescription = "homescale home bg",
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier
                .padding(top = 120.dp)
                .size(235.dp)
                .align(Alignment.TopCenter),

            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .padding(top = 162.dp)
                .size(139.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.ic_hamcoach_normal),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .padding(24.dp)
                .size(24.dp)
                .align(Alignment.TopStart)
                .clickable { navController.navigate(Route.Home.route) },
            painter = painterResource(id = R.drawable.ic_back_arow),
            contentDescription = "",
        )
        Box(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 93.dp, width = 248.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "최근 아쉬웠던\n 부분들이야!",
                style = DungGeunMo15,
                lineHeight = 20.sp,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(248.dp)
                    .align(Alignment.TopCenter)
            )
        }

        Image(
            modifier = Modifier
                .padding(24.dp)
                .size(24.dp)
                .align(Alignment.TopStart),
//                .clickable { navController.navigate(Route.Home.route) },
            painter = painterResource(id = R.drawable.ic_back_arow),
            contentDescription = "",
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxHeight(0.6f)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                        )
                    )
                    .border(
                        width = 1.25.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 30.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "<햄코치의 관찰일지>",
                    lineHeight = 28.sp,
                    style = DungGeunMo20,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 14.39.dp)
                )

                val observeList = listOf(
                    "외식 2회",
                    "공복 시간 적음",
                    "잦은 술자리",
                    "수시로 배달 어플",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                    "잦은 야식",
                )

                Box() {
                    LazyColumn(
                        state = lazyState,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 70.dp)
                    ) {
                        items(observeList) { observe ->
                            ObservationBox(
                                value = observe,
                                navController = navController
                            )
                        }
                    }
                    VerticalScrollbar(
                        scrollState = lazyState,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxHeight(0.7f)
                            .padding(end = 15.dp, top = 60.dp)
                    )
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

