package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.presentation.home.component.ObservationBox
import com.konkuk.kuit_kac.presentation.home.component.VerticalScrollbar
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeObservationScreen(modifier: Modifier = Modifier) {
    val lazyState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
                            value = observe
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

@Preview(showBackground = true)
@Composable
private fun HomeObservationScreenPreview() {
    HomeObservationScreen()
}

