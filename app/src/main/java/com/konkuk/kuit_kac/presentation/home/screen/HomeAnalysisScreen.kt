package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeMultipleNutritionBar
import com.konkuk.kuit_kac.presentation.home.component.HomeNutritionLabel
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeAnalysisScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RectangleShape)
    ) {
        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 400.73f.hp())
        )
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(275f.wp())
                .padding(top = 30f.hp()),
            painter = painterResource(R.drawable.img_homegraphscreen_nutritiontextballoon),
            contentDescription = "최근 아쉬웠던 부분들이야!"
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 110f.hp())
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
                        top = 117.6f.bhp(),
                        start = 110.35f.wp()
                    )
                    .size(48.02214f.wp(),48.02214f.bhp()),
                painter = painterResource(R.drawable.img_homegraphscreen_magnifyingglass),
                contentDescription = "magnifying glass"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 358f.hp(),
                    start = 24f.wp(),
                    end = 24f.wp()
                )
                .clip(RoundedCornerShape(
                    topStart = 30f.wp(),
                    topEnd = 30f.wp()
                ))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF,),Color(0xFFFFEDD0))
                    )
                )
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(
                    topStart = 30f.wp(), topEnd = 30f.wp()
                )),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 14.39f.bhp()),
                text = "<현재까지의 외식 영양소>",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF000000)
            )
            HorizontalDivider(modifier = Modifier
                .padding(top = 15.39f.bhp())
                .width(300.24902f.wp())
                .background(color = Color(0xFF000000))
            )
            HomeMultipleNutritionBar(
                modifier = Modifier
                    .padding(top = 16.26f.bhp()),
                carb = 32f, protein = 43f, fat = 23f, healthy = 12f
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 35.34f.wp(), top = 24.34f.bhp()),
                verticalArrangement = Arrangement.spacedBy(20f.bhp())
            ) {
                HomeNutritionLabel(content = "고탄수화물 음식", color = 0xFFFFD387)
                HomeNutritionLabel(content = "고단백 음식", color = 0xFFCBF38E)
                HomeNutritionLabel(content = "고지방 위주 음식", color = 0xFFFFA4A7)
                HomeNutritionLabel(content = "건강식/저열량 식단", color = 0xFFD2D2D2)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAnalysisScreenPreview(){
    HomeAnalysisScreen()
}