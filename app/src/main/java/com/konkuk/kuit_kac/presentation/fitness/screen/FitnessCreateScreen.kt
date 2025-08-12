package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun FitnessCreateScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineViewModel: RoutineViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var hasFocus by remember { mutableStateOf(false) } // 포커스 상태 추적

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.img_fitness_textballoon),
            contentDescription = "text balloon",
            modifier = Modifier
                .padding(top = 12f.hp(), start = 78f.wp())
                .size(272f.wp(), 98f.bhp())
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            HamcoachGif(
                modifier = Modifier.offset(y = 84f.hp()),
                num = 1,
                ellipseLength = 177.17575,
                mascotLength = 145.0,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 256f.hp(), start = 24f.wp(), end = 24f.wp())
                .height(458f.bhp())
                .clip(shape = RoundedCornerShape(20f.bhp()))
                .background(color = Color(0xFFFFF1AB))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 22f.bhp())
                    .background(color = Color(0xFFFFFCEE))
            ) {
                TextField(
                    modifier = Modifier
                        .width(176f.wp())
                        .heightIn(min = 56f.bhp())
                        .onFocusChanged { focusState ->
                            hasFocus = focusState.isFocused
                        },
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        // 값이 없고 포커스가 없을 때만 표시
                        if (title.isEmpty() && !hasFocus) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "제목을 입력해줘",
                                    textAlign = TextAlign.Center,
                                    style = DungGeunMo17,
                                    fontSize = 17f.isp(),
                                    color = Color(0xFF999999)
                                )
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = DungGeunMo17.copy(
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20f.bhp(), start = 16f.wp(), end = 15f.wp())
                    .height(84f.bhp())
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color(0xFFFFFFFF))
                    .clickable(
                        onClick = {
                            routineViewModel.setName(title)
                            navController.navigate(Route.FitnessSearch.route)
                        }
                    )
                    .drawBehind {
                        val strokeWidth = 3.dp.toPx()
                        val pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                            floatArrayOf(4.dp.toPx(), 4.dp.toPx()), 0f
                        )
                        drawRoundRect(
                            color = Color.Black,
                            style = Stroke(width = strokeWidth, pathEffect = pathEffect),
                            size = size,
                            cornerRadius = CornerRadius(15.dp.toPx())
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(19f.wp(), 19f.bhp())
                        .padding(end = 7f.wp()),
                    painter = painterResource(R.drawable.img_diet_plus),
                    contentDescription = "plus"
                )
                Text(
                    text = "운동 추가하기",
                    style = DungGeunMo15,
                    fontSize = 15f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessCreateScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    FitnessCreateScreen(navController = navController)
}