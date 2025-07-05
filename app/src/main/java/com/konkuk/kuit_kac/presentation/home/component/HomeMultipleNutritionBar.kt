package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15

@Composable
fun HomeMultipleNutritionBar(
    modifier: Modifier = Modifier,
    carb: Float,
    protein: Float,
    fat: Float,
    healthy:Float
    ) {
    /*val whole = carb + protein + fat + healthy
    fun percentageMaker(input: Int): Double{
        return (input.toDouble())/whole
    }
    val carbPercentage = percentageMaker(carb);
    val proteinPercentage = percentageMaker(protein);
    val percentageMaker(fat);
    percentageMaker(healthy) */
    Row(
        modifier = modifier
            .size(329.dp, 53.6123.dp)
            .clip(RoundedCornerShape(78.dp))
            .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFF000000))
            .border(1.dp,Color(0xFF000000), shape = RoundedCornerShape(78.dp))
    ){
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(carb)
                .background(color = Color(0xFFFFD387)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = carb.toString() + "%",
                style = DungGeunMo15
            )
        }
        // TODO float 쓸지 int 쓸지 물어보고 component화 하기
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(protein)
                .background(color = Color(0xFFCBF38E)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = protein.toString() + "%",
                style = DungGeunMo15
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(fat)
                .background(color = Color(0xFFFFA4A7)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = fat.toString() + "%",
                style = DungGeunMo15
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(healthy)
                .background(color = Color(0xFFD2D2D2)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = (healthy.toInt()).toString() + "%",
                style = DungGeunMo15
            )
        }
    }
}

