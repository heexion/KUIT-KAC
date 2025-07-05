package com.konkuk.kuit_kac.presentation.home.homegraph.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.homegraph.component.HomeGraphScreenRow
import com.konkuk.kuit_kac.presentation.home.homegraph.component.NutritionGraph
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeAnalysisScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RectangleShape)
    ) {
        HomeBackgroundComponent()
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(275.dp)
                .padding(top = 70.dp),
            painter = painterResource(R.drawable.img_homegraphscreen_nutritiontextballoon),
            contentDescription = "최근 아쉬웠던 부분들이야!"
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 163.dp)
        ) {
            EllipseNyam(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                ellipseLength = 248.0,
                mascotLength = 178.73438
            )
            Image(
                modifier = Modifier
                    .padding(
                        top = 117.6.dp,
                        start = 110.35.dp
                    )
                    .size(48.02214.dp),
                painter = painterResource(R.drawable.img_homegraphscreen_magnifyingglass),
                contentDescription = "magnifying glass"
            )
        }
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 477.73.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 398.dp,
                    start = 24.dp,
                    end = 24.dp
                )
                .clip(RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF,),Color(0xFFFFEDD0))
                    )
                )
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(
                    topStart = 30.dp, topEnd = 30.dp
                )),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 14.39.dp),
                text = "<현재까지의 외식 영양소>",
                style = DungGeunMo20
            )
            HorizontalDivider(modifier = Modifier
                .padding(top = 15.39.dp)
                .width(300.24902.dp)
                .background(color = Color.Black)
            )
            NutritionGraph(
                modifier = Modifier
                    .padding(top = 16.26.dp),
                carb = 32f, protein = 43f, fat = 23f, healthy = 12f
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 35.34.dp, top = 24.34.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomeGraphScreenRow(content = "고탄수화물 음식", color = 0xFFFFD387)
                HomeGraphScreenRow(content = "고단백 음식", color = 0xFFCBF38E)
                HomeGraphScreenRow(content = "고지방 위주 음식", color = 0xFFFFA4A7)
                HomeGraphScreenRow(content = "건강식/저열량 식단", color = 0xFFD2D2D2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAnalysisScreenPreview(){
    HomeAnalysisScreen()
}