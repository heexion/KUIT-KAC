package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun HomeSingleNutritionBar(
    modifier: Modifier = Modifier,
    type: String,
    base: Int,
    quantity: Int
    ) {
    val percentage = quantity.toDouble()/base
    var widthPercentage = percentage
    if(percentage > 1){
        widthPercentage = 1.0
    }
    val width = (widthPercentage * 332).toInt()
    val color = when(type) {
        "탄수화물" -> Color(0xFFFFD387)
        "단백질" -> Color(0xFFCBF38E)
        "당류" -> Color(0xFFABDBFF)
        "지방" -> Color(0xFFFFA4A7)
        else -> Color.White
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = type,
                style = DungGeunMo17
            )
            Text(
                modifier = Modifier
                    .padding(start = 12.65.dp),
                text = quantity.toString(),
                style = DungGeunMo17,
                color = color
            )
            Text(
                "/" + base.toString(),
                style = DungGeunMo17
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 7.17.dp)
                .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                .size(332.dp,36.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                .background(color = Color(0xFFD8D8D8))
        ) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
                    .width(width.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(30.dp))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                    .background(color = color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ((percentage*100).toInt()).toString() + "%",
                    style = DungGeunMo24
                )
            }
        }
    }
}