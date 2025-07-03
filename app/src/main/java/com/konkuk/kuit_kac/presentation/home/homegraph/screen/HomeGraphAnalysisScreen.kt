package com.konkuk.kuit_kac.presentation.home.homegraph.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.homegraph.component.HomeGraphScreenColumn
import com.konkuk.kuit_kac.ui.theme.Purple80

@Composable
fun HomeGraphAnalysisScreen(modifier: Modifier = Modifier) {
    Box(
        Modifier.fillMaxSize()
    ) {
        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 477.73.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
            ) {
                Image(
                    modifier = Modifier
                        .size(248.0013.dp, 103.00002.dp),
                    painter = painterResource(R.drawable.img_homegrpahscreen_analysistextballoon),
                    contentDescription = "현재까지 내 뱃속 정보야"
                )
                Image(
                    modifier = Modifier
                        .size(187.67596.dp, 244.dp)
                        .offset(y = 86.dp)
                        .align(Alignment.BottomCenter),
                    painter = painterResource(R.drawable.img_main_person),
                    contentDescription = "person"
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 103.dp)
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                        topStart = 75.dp,
                        topEnd = 75.dp
                    )
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF,), Color(0xFFFFEDD0))
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Column(
                    modifier = Modifier
                        .padding(top = 29.87.dp, start = 40.dp, end = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeGraphScreenColumn(
                        type = "탄수화물",
                        base = 157,
                        quantity = 130
                    )
                    HomeGraphScreenColumn(
                        type = "단백질",
                        base = 117,
                        quantity = 20
                    )
                    HomeGraphScreenColumn(
                        type = "당류",
                        base = 157,
                        quantity = 130
                    )
                    HomeGraphScreenColumn(
                        type = "지방",
                        base = 76,
                        quantity = 104
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeGraphAnalysisScreenPreview(){
    HomeGraphAnalysisScreen()
}