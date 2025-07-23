package com.konkuk.kuit_kac.presentation.mealdiet.meal.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20


@Composable
fun MealTopBarWithSearch(
    modifier: Modifier,
    title: String,
    placeholderText: String = "무슨 음식을 먹었어?",
    placeholderTextColor: Color = Color(0xFFB5B5B5),
    placeholderTextStyle: TextStyle = DungGeunMo15,
    showClearButton: Boolean = false,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 50f.bhp(),
                    bottomEnd = 50f.bhp()
                )
            )
            .background(Color(0xFFFFF1AB))
            .padding(horizontal = 20f.wp(), vertical = 20f.bhp())
    ) {
        Column {
            // 상단바
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 뒤로가기 아이콘
                Image(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(24f.wp(),24f.bhp())
                        .clickable { onBackClick() }
                )

                // 타이틀
                Text(
                    text = title,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF713E3A),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(23f.bhp()))

            // 검색 바
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48f.bhp())
                    .clip(RoundedCornerShape(30f.bhp()))
                    .background(Color(0xFFFFFFFF))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(30f.bhp()))
                    .clickable { onSearchClick() },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20f.wp()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = placeholderText,
                        style = placeholderTextStyle,
                        fontSize = 15f.isp(),
                        color = placeholderTextColor
                    )

                    if (showClearButton) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_xmark),
                            contentDescription = "Clear Search",
                            modifier = Modifier
                                .size(24f.wp(),24f.bhp())
                                .clickable {
                                    onClearClick()
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMealTopBarWithSearch() {
    MealTopBarWithSearch(
        title = "식단 검색하기",
        placeholderText = "무슨 음식을 먹었어?",
        placeholderTextColor = Color.Gray,
        placeholderTextStyle = DungGeunMo17,
        onBackClick = {},
        onSearchClick = {},
        onClearClick = { },
        modifier = Modifier
    )
}


