
package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

data class MealCardData(
    val mealType: String,
    val totalKcal: String,
    val foodList: List<Triple<Painter, String, String>>
)


@Composable
fun MealMainScreen(
    navController: NavController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    onRecordClick: () -> Unit,
    onFastedClick: () -> Unit,
    mealCards: List<MealCardData>

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 상단 배경
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .padding(
                        start = 24f.wp(), end = 24f.wp(),
                        top = 16f.hp(), bottom = 14.5f.bhp()
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 타이틀
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_diet),
                            contentDescription = "식단 아이콘",
                            modifier = Modifier.size(28.8584f.wp(),28.8584f.bhp())
                        )
                        Spacer(modifier = Modifier.width(4f.wp()))
                        Text(
                            text = "식단",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF713E3A)
                        )
                    }

                    Spacer(modifier = Modifier.height(17.5f.bhp()))

                    // 탭 버튼
                    Row(modifier = Modifier.fillMaxWidth()) {
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "식단 기록",
                            isSelected = selectedTab == "기록",
                            buttonHeight = 49,
                            onClick = { onTabClick("기록") }
                        )
                        Spacer(modifier = Modifier.width(16.21f.wp()))
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "나만의 식단",
                            isSelected = selectedTab == "나만의",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate(Route.Diet.route)
                                onTabClick("나만의")
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1f.bhp())
                    .background(Color(0xFF000000))
            )

            // 메인 콘텐츠
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFFF3C1),
                                Color(0xFFFFFFFF),
                                Color(0xFFFFF3C1)
                            )
                        )
                    )
                    .padding(horizontal = 24f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(28f.bhp()))

                RecordMealButton(
                    onClick = { navController.navigate(Route.MealRecord.route) }
                )

                Spacer(modifier = Modifier.height(27.22f.bhp()))

                if (mealCards.isEmpty()) {
                    // 식단이 없을 때
                    Box(
                        modifier = Modifier
                            .width(364f.wp())
                            .height(458f.bhp())
                            .clip(RoundedCornerShape(20f.bhp()))
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_meal_bg),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )

                        Box(
                            modifier = Modifier
                                .padding(top = 87f.bhp(), start = 72.67f.wp())
                        ) {
                            SpeechBubble(messageText = "현재 식단이\n비어있어요!",
                                modifier = Modifier)
                            EllipseNyam(ellipseLength = 182.0, mascotLength = 109.0,
                                modifier = Modifier
                                    .offset(y = 73f.bhp(), x = 19f.wp())
                                    .clickable(
                                        onClick = {
                                            navController.navigate(Route.MealExist.route)
                                        }
                                    ))
                            OutlinedRoundedButton(
                                modifier = Modifier
                                    .offset(x = 47f.wp(),y=255f.bhp()),
                                value = "단식했어!",
                                onClick = {
                                    navController.navigate(Route.MealFastingResult.route)
                                }
                            )
                        }
                    }
                } else {
                    // 식단이 있는 경우 MealCard 목록 출력
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        mealCards.forEach { card ->
                            MealCard(
                                mealType = card.mealType,
                                totalKcal = card.totalKcal,
                                foodList = card.foodList,
                                onEditClick = { navController.navigate(Route.MealPatch.route) }
                            )
                        }
                    }
                }

            }
        }

        // 캘린더 플로팅 버튼 (오른쪽 하단) 디자인 제약때문에 Box로 구현함
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-25).dp, y = (-93).dp)
                .size(61.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color(0xFFFFB638)
                        )
                    )
                )
                .border(1.dp, Color.Black, CircleShape)
                .alpha(0.85f)
                .clickable { /* onCalendarClick() */ },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "캘린더",
                modifier = Modifier.size(42.dp)
            )
        }
    }
}

@Composable
fun CalendarFloatingButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(61.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF), // 위쪽 흰색
                        Color(0xFFFFB638)  // 아래쪽 주황색
                    )
                )
            )
            .border(1.dp, Color.Black, shape = CircleShape)
            .shadow(elevation = 4.dp, shape = CircleShape, ambientColor = Color.Black.copy(alpha = 0.1f))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = "Calendar"
        )
    }
}

@Composable
fun SpeechBubble(messageText: String,
                 modifier: Modifier) {
    Box(
        modifier = modifier
            .width(219f.wp())
            .height(83f.bhp()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
            contentDescription = null,
            modifier = Modifier.matchParentSize()  // 말풍선 전체 크기 채움
        )
        Text(
            text = messageText,
            style = DungGeunMo20.copy(fontSize = 20f.isp()),
            lineHeight = 28.sp,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 21f.bhp())
        )
    }
}



@Composable
fun OutlinedRoundedButton(
    modifier: Modifier,
    value: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(125f.wp())
            .height(48f.bhp())
            .clip(RoundedCornerShape(20f.bhp()))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(20f.bhp())),
            painter = painterResource(id = R.drawable.bg_orange_button_default),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000)
        )
    }
}

@Composable
fun RecordMealButton(
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
            painter = painterResource(id = R.drawable.ic_record),
            contentDescription = "식단 기록 아이콘",
            modifier = Modifier.size(27f.wp(),27f.bhp())
        )
        Spacer(modifier = Modifier.width(8f.wp()))
        Text(
            text = "식단 기록하기",
            style = DungGeunMo20.copy(fontSize = 17f.isp()),
            color = Color(0xFF000000)
        )
    }
}


//식단 있을때
@Preview(showBackground = true)
@Composable
private fun MealMainScreenPreview() {
    val navController = rememberNavController()

    MealMainScreen(
        navController = navController,
        selectedTab = "기록",
        onTabClick = {},
        onRecordClick = {},
        onFastedClick = {},
        mealCards = listOf(
            MealCardData(
                mealType = "아침",
                totalKcal = "420kcal",
                foodList = listOf(
                    Triple(painterResource(id = R.drawable.ic_sweetpotato), "고구마", "100g"),
                    Triple(painterResource(id = R.drawable.ic_salad), "샐러드", "50g"),
                    Triple(painterResource(id = R.drawable.ic_chickenbreast), "닭가슴살", "80g")
                )
            )
        )
    )
}

////식단 없을때
//@Preview(showBackground = true)
//@Composable
//private fun MealMainScreenEmptyPreview() {
//    val navController = rememberNavController()
//
//    MealMainScreen(
//        navController = navController,
//        selectedTab = "기록",
//        onTabClick = {},
//        onRecordClick = {},
//        onFastedClick = {},
//        mealCards = emptyList() //식단 없음
//    )
//}
