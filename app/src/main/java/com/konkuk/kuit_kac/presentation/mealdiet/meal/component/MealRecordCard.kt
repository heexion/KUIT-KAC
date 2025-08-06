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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.screen.RecordMealButton
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealRecordCard(
    mealType: String,
    navController: NavHostController,
    onFastingClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(364f.wp())
            .wrapContentHeight()
            .clip(RoundedCornerShape(16f.bhp()))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(16f.bhp()))
            .background(Color(0xFFFFFFFF))
    ) {
        // 헤더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.855f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_diet_card),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16f.wp(), vertical = 11.1f.bhp()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$mealType ",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }
        }

        // 배경 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(154f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_diet_card_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(bottomStart = 16f.bhp(), bottomEnd = 16f.bhp()))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16f.wp(),
                        end = 16f.wp(),
                        top = 20.45f.bhp(),
                        bottom = 16f.bhp()
                    ),
                verticalArrangement = Arrangement.spacedBy(12f.bhp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RecordMealButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                            navController.navigate("MealGraph/${mealType}")

                    }
                )

                FastingMealButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onFastingClick
                )
            }
        }
    }
}


@Composable
fun FastingMealButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(364f.wp())
            .height(49f.bhp())
            .border(
                width = 1.dp,
                color = Color(0xFF000000),
                shape = RoundedCornerShape(35f.wp())
            )
            .clickable { onClick() }
            .padding(horizontal = 16f.wp()), // 내부 여백
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fasting),
            contentDescription = "단식 기록 아이콘",
            modifier = Modifier.size(27f.wp(),27f.bhp())
        )
        Spacer(modifier = Modifier.width(8f.wp()))
        Text(
            text = "단식했어!",
            style = DungGeunMo20.copy(fontSize = 17f.isp()),
            color = Color(0xFF000000)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMealRecordCard() {
    val navController = rememberNavController()
    MealRecordCard(
        mealType = "아침",
        navController = navController,
        onFastingClick = {
            // 예: 해당 카드 제거 또는 토스트 메시지 출력 등 원하는 로직
        }
    )
}
