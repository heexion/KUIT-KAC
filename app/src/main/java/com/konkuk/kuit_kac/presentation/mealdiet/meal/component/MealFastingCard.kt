package com.konkuk.kuit_kac.presentation.mealdiet.meal.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealFastingCard(
    mealType: String,
    navController: NavHostController,
    onCloseClick: () -> Unit
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
            // bg_fastingcard + 닫기 아이콘
            Box(
                modifier = Modifier
                    .padding(horizontal = 16f.wp(), vertical = 20f.bhp())
                    .size(width = 332f.wp(), height = 114f.bhp())
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_fastingcard),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "닫기",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10f.bhp(), end = 11f.wp())
                        .size(19f.wp())
                        .noRippleClickable { onCloseClick() },
                    colorFilter = ColorFilter.tint(Color(0xFF000000))
                )
            }

            // 캐릭터 + 말풍선 + 텍스트
            Row(
                modifier = Modifier
                    .padding(horizontal = 32f.wp(), vertical = 32f.bhp())
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 캐릭터 + 그림자
                Box(
                    modifier = Modifier
                        .width(98f.wp())
                        .height(96f.bhp() + 8f.bhp())
                ) {
                    // 그림자
                    Image(
                        painter = painterResource(id = R.drawable.ic_fastingbacklight),
                        contentDescription = "그림자",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .width(37f.wp())
                            .height(8f.bhp())
                            .offset(y = 4f.bhp())
                    )

                    // 캐릭터
                    Image(
                        painter = painterResource(id = R.drawable.ic_fastingnyam),
                        contentDescription = "캐릭터",
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .width(98f.wp())
                            .height(96f.bhp())
                    )
                }

                // 말풍선 + 텍스트
                Box(
                    modifier = Modifier
                        .width(159f.wp())
                        .height(57.82f.bhp())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_fastingbubble),
                        contentDescription = "말풍선",
                        modifier = Modifier.matchParentSize()
                    )

                    Text(
                        text = "${mealType}은 굶었어!",
                        style = DungGeunMo15,
                        fontSize = 14f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(
                                top = 14f.bhp(),
                                bottom = 23.82f.bhp(),
                                start = 27f.wp(),
                                end = 27f.wp()
                            ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMealFastingCard() {
    val navController = rememberNavController()

    MealFastingCard(
        mealType = "점심",
        navController = navController,
        onCloseClick = {
            // 예: 해당 카드 제거 또는 토스트 메시지 출력 등 원하는 로직
            println("닫기 버튼 눌림")
        }
    )
}
