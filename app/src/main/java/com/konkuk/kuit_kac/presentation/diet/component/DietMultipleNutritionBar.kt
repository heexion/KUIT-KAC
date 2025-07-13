package com.konkuk.kuit_kac.presentation.diet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15

@Composable
fun DietMultipleNutritionBar(
    modifier: Modifier = Modifier,
    carb: Float,
    protein: Float,
    fat: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 17f.wp(), end = 13f.bhp()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                .width(19.42188f.wp())
                .height(19.42188f.wp())
                .clip(RoundedCornerShape(9.71094f.wp()))
                .background(color = Color(0xFFFFD387))
                .border(width = 1.dp, color = Color(0xFF000000), RoundedCornerShape(9.71094f.wp()))
        )
        Box(
            modifier = Modifier
                .padding(start = 0.29f.wp())
                .size(68f.wp(),20f.bhp()),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "탄 360.2g",
                textAlign = TextAlign.Center,
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8f.wp())
                .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                .width(19.42188f.wp())
                .height(19.42188f.wp())
                .clip(RoundedCornerShape(9.71094f.wp()))
                .background(color = Color(0xFFCBF38E))
                .border(width = 1.dp, color = Color(0xFF000000), RoundedCornerShape(9.71094f.wp()))
        )
        Box(
            modifier = Modifier
                .padding(start = 0.29f.wp())
                .size(68f.wp(),20f.bhp()),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "단 123g",
                textAlign = TextAlign.Center,
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 8f.wp())
                .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                .width(19.42188f.wp())
                .height(19.42188f.wp())
                .clip(RoundedCornerShape(9.71094f.wp()))
                .background(color = Color(0xFFFFA4A7))
                .border(width = 1.dp, color = Color(0xFF000000), RoundedCornerShape(9.71094f.wp()))
        )
        Box(
            modifier = Modifier
                .padding(start = 0.29f.wp())
                .size(68f.wp(),20f.bhp()),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "지 51.8g",
                textAlign = TextAlign.Center,
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(53.6f.bhp())
            .clip(RoundedCornerShape(26.8f.bhp()))
            .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
            .border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(26.8f.bhp()))
    ){
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(carb)
                .background(color = Color(0xFFFFD387)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10f.wp()),
                text = carb.toInt().toString() + "%",
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(protein)
                .background(color = Color(0xFFCBF38E)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10f.wp()),
                text = protein.toInt().toString() + "%",
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(fat)
                .background(color = Color(0xFFFFA4A7)),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                modifier = Modifier
                    .padding(start = 10f.wp()),
                text = fat.toInt().toString() + "%",
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000)
            )
        }
    }
}

