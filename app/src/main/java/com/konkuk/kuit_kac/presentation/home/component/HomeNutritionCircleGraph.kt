package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeNutritionCircleGraph(
    modifier: Modifier = Modifier,
    current: Int,
    goal: Int,
    left: Int
) {
    val angle = (left.toFloat() / goal) * 360
    val bigRadius = 95.0703; val smallRadius = 41
    val hypotenuse = bigRadius/2 + smallRadius/2
    val rad = Math.toRadians((angle/2).toDouble())
    val x = -hypotenuse * sin(rad)
    val y = -hypotenuse * cos(rad)

    Box(
        modifier = modifier
            .size(190.1416.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(95.0708.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                )
            )
            .drawBehind {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 2
                val startAngle = 270f
                val endAngle = (startAngle - angle + 360f) % 360f

                val thickStroke = 2.dp.toPx()
                val thinStroke = 1.dp.toPx()
                val fillPath = androidx.compose.ui.graphics.Path().apply {
                    moveTo(center.x, center.y)
                    arcTo(
                        rect = Rect(center, radius - thickStroke),
                        startAngleDegrees = endAngle,
                        sweepAngleDegrees = angle,
                        forceMoveTo = false
                    )
                    close()
                }
                drawPath(fillPath, color = Color(0xFFEEEEEE))
                drawArc(
                    color = Color.Black,
                    startAngle = (endAngle + angle) % 360f,
                    sweepAngle = 360f - angle,
                    useCenter = false,
                    topLeft = Offset(
                        center.x - (radius - thickStroke / 2),
                        center.y - (radius - thickStroke / 2)
                    ),
                    size = Size((radius - thickStroke / 2) * 2, (radius - thickStroke / 2) * 2),
                    style = Stroke(width = thickStroke)
                )
                drawArc(
                    color = Color.Black,
                    startAngle = endAngle,
                    sweepAngle = angle,
                    useCenter = false,
                    topLeft = Offset(
                        center.x - (radius - thinStroke / 2),
                        center.y - (radius - thinStroke / 2)
                    ),
                    size = Size((radius - thinStroke / 2) * 2, (radius - thinStroke / 2) * 2),
                    style = Stroke(width = thinStroke)
                )
            },
        contentAlignment = Alignment.Center
    ) {


        Box(
            modifier = Modifier
                .size(82.dp)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                    )
                )
                .border(2.dp, Color.Black, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "오늘의\n칼로리",
                style = DungGeunMo20,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Box(
                modifier = Modifier
                    .size(50.dp, 40.dp)
                    .offset(
                        x = x.dp,
                        y =  y.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = left.toString() + "\nkcal",
                    style = DungGeunMo17,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

