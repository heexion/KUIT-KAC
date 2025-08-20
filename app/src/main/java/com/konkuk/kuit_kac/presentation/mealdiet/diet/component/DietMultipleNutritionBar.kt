package com.konkuk.kuit_kac.presentation.mealdiet.diet.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15

@Composable
fun DietMultipleNutritionBar(
    modifier: Modifier = Modifier,
    carb: Float,
    protein: Float,
    fat: Float,
) {
    data class Seg(val key: String, val value: Float, val color: Color)

    val all = listOf(
        Seg("탄", carb, Color(0xFFFFD387)),
        Seg("단", protein, Color(0xFFCBF38E)),
        Seg("지", fat, Color(0xFFFFA4A7)),
    )
    val segments = all.filter { it.value > 0f }

    val total = carb + protein + fat
    val totalSafe = if (total > 0f) total else 1f
    if (segments.isNotEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 41f.wp()),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            segments.forEachIndexed { index, s ->
                if (index > 0) Spacer(Modifier.width(8f.wp()))
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 0.dp,
                            spotColor = Color(0xFF000000),
                            ambientColor = Color(0xFF000000)
                        )
                        .width(19.42188f.bhp())
                        .height(19.42188f.bhp())
                        .clip(RoundedCornerShape(9.71094f.bhp()))
                        .background(color = s.color)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF000000),
                            RoundedCornerShape(9.71094f.bhp())
                        )
                )
                Box(
                    modifier = Modifier
                        .padding(start = 0.29f.wp())
                        .size(68f.wp(), 20f.bhp()),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${s.key} ${String.format("%.1f", s.value)}g",
                        textAlign = TextAlign.Center,
                        style = if (s.key == "탄") DungGeunMo12 else DungGeunMo15,
                        fontSize = 13f.isp(),
                        color = Color(0xFF000000)
                    )
                }
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(53.6f.bhp())
            .clip(RoundedCornerShape(26.8f.bhp()))
            .shadow(
                elevation = 0.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(26.8f.bhp()))
    ) {
        segments.forEach { s ->
            val percent = ((s.value / totalSafe) * 100f).toInt()
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(s.value)
                    .background(color = s.color),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier.padding(start = 10f.wp()),
                    text = "$percent%",
                    style = DungGeunMo15,
                    fontSize = 15f.isp(),
                    color = Color(0xFF000000)
                )
            }
        }
    }
}
