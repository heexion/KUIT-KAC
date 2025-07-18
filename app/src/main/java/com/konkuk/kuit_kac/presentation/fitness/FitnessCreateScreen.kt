package com.konkuk.kuit_kac.presentation.fitness

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun FitnessCreateScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var title by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32f.bhp()), // dp → bhp 변환
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                contentDescription = "말풍선",
                modifier = Modifier
                    .size(width = 310f.wp(), height = 105f.bhp()) // dp → wp, bhp
            )
            Text(
                text = "아직 너만의 운동 루틴이 없어!\n만들어줄래?",
                style = DungGeunMo17,
                lineHeight = 28f.isp(), // sp → isp
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 2f.bhp(), bottom = 20f.bhp()) // dp → bhp
            )
        }

        EllipseNyam(
            modifier = Modifier
                .padding(top = 115f.hp(), start = 117f.wp()),
            mascotLength = 106.1115,
            ellipseLength = 177.17575
        )

        Spacer(modifier = Modifier.height(48f.bhp())) // dp → bhp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 256f.hp(), start = 24f.wp(), end = 24f.wp())
            .height(458f.wp())
            .clip(shape = RoundedCornerShape(20f.wp()))
            .background(color = Color(0xFFFFF1AB))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
            .clickable {
                navController.navigate("fitness_search")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 22f.bhp())
                .background(color = Color(0xFFFFFCEE))
        ) {
            TextField(
                modifier = Modifier
                    .width(190f.wp())
                    .heightIn(min = 28f.bhp()),
                value = title,
                onValueChange = { title = it },
                placeholder = {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "제목을 입력해주세요",
                            textAlign = TextAlign.Center,
                            style = DungGeunMo17,
                            fontSize = 17f.isp(), // sp → isp
                            color = Color(0xFF000000)
                        )
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
                .height(84f.bhp()) // dp → bhp
                .clip(RoundedCornerShape(15f.wp()))
                .background(color = Color(0xFFFFFFFF))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.wp()))
                .clickable {
                    navController.navigate("fitness_search")
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


@Preview(showBackground = true)
@Composable
fun FitnessCreateScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    FitnessCreateScreen(navController = navController)
}