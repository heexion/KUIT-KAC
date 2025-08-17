
package com.konkuk.kuit_kac.presentation.mealdiet.meal.component


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

data class FoodItem(
    val icon: Painter,
    val name: String,
    val quantity: String,
    val isNightSnack: Boolean = false
)

@Composable
fun MealCard(
    mealType: String,
    totalKcal: String,
    foodList: List<FoodItem>,
    onEditClick: () -> Unit,
    mealViewModel: MealViewModel
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("meal_prefs", Context.MODE_PRIVATE)

    // mealType 상관 없이: isNightSnack 있는지 + 아직 한 번도 안 보여줬는지
    val shouldShowNightSnackTip = remember {
        mutableStateOf(
            foodList.any { it.isNightSnack } &&
                    !prefs.getBoolean("night_snack_tip_shown", false)
        )
    }

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
                Text(
                    text = "(총 $totalKcal)",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    color = Color(0xFF713E3A)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .padding(top = 22f.bhp(), start = 13.9f.wp())
                        .size(26.75811f.wp(), 26.75811f.bhp())
                        .clip(RoundedCornerShape(13.27905f.bhp()))
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(13.27905f.bhp()),
                            color = Color(0xff000000)
                        )
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .noRippleClickable { onEditClick() },
                    contentAlignment = Alignment.Center
                ) {
                    // 레코드 아이콘 (배경 역할)
                    Image(
                        painter = painterResource(id = R.drawable.ic_record),
                        contentDescription = "Record Icon",
                        modifier = Modifier
                            .width(24f.wp())
                            .height(24f.bhp())
                            .offset(x = (-2f).wp())
                    )

                    // 펜 아이콘 (위에 올림)
                    Image(
                        painter = painterResource(id = R.drawable.ic_button_pencil),
                        contentDescription = "Pencil Icon",
                        modifier = Modifier
                            .width(24f.wp())
                            .height(24f.bhp())
                            .offset(x = (-2f).wp())
                    )
                }

            }
        }

        // 본문: 배경 + 음식 리스트 + 말풍선 오버레이
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(154f.bhp())
        ) {
            // 카드 배경
            Image(
                painter = painterResource(id = R.drawable.img_diet_card_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16f.bhp(),
                            bottomEnd = 16f.bhp()
                        )
                    )
            )

            // 음식 리스트
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 17f.wp()),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                foodList.forEach { item ->
                    Box(
                        modifier = Modifier
                            .width(76f.wp())
                            .height(135f.bhp())
                    ) {
                        if (item.isNightSnack) {
                            Image(
                                painter = painterResource(id = R.drawable.bg_nightsnack),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(76f.wp(), 135f.bhp())
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Image(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.size(72f.wp(), 72f.bhp())
                            )
                            Spacer(modifier = Modifier.height(6f.bhp()))
                            Text(
                                text = item.name,
                                style = DungGeunMo15,
                                fontSize = 15f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(2f.bhp()))
                            Text(
                                text = item.quantity,
                                style = DungGeunMo12,
                                fontSize = 12f.isp(),
                                color = Color(0xFF713E3A),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // 첫 번째 야식 아이템 위에만 말풍선 표시 (처음 1번)
            if (shouldShowNightSnackTip.value) {
                val firstNightSnackIndex = foodList.indexOfFirst { it.isNightSnack }
                if (firstNightSnackIndex != -1) {
                    // Row의 SpaceEvenly 간격 근사치로 X 위치 계산
                    val itemSpacing =
                        (364f.wp() - (76f.wp() * foodList.size)) / (foodList.size + 1)
                    val bubbleXOffset =
                        itemSpacing * (firstNightSnackIndex + 1) +
                                (76f.wp() * firstNightSnackIndex) +
                                (76f.wp() / 2) - (185.545f.wp() / 2)

                    Image(
                        painter = painterResource(id = R.drawable.img_bubble_nightsnack),
                        contentDescription = "야식 안내",
                        modifier = Modifier
                            .absoluteOffset(
                                x = bubbleXOffset,
                                y = (-40).dp
                            )
                            .size(width = 185.545f.wp(), height = 81f.bhp())
                    )

                    // 보였으면 기록해두고 다음부턴 안 보이게
                    LaunchedEffect(Unit) {
                        prefs.edit().putBoolean("night_snack_tip_shown", true).apply()
                      //  shouldShowNightSnackTip.value = false
                    }
                }
            }
        }
    }
}


//테스트용
@Composable
fun MealCardTestMode(
    mealType: String,
    totalKcal: String,
    foodList: List<FoodItem>,
    onEditClick: () -> Unit,
    mealViewModel: MealViewModel
) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("meal_prefs", Context.MODE_PRIVATE)

    val shouldShowNightSnackTip =
        mealType == "야식" &&
                foodList.any { it.isNightSnack } &&
                !prefs.getBoolean("night_snack_tip_shown", false)

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
                Text(
                    text = "(총 $totalKcal)",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    color = Color(0xFF713E3A)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(30.16f.wp(), 29.66f.bhp())
                        .noRippleClickable { onEditClick() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_button_pencil),
                        contentDescription = "Edit Button",
                        modifier = Modifier.matchParentSize()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_record),
                        contentDescription = "Pencil Icon",
                        modifier = Modifier
                            .size(26.76f.wp(), 26.76f.bhp())
                            .align(Alignment.Center)
                    )
                }
            }
        }

        // 배경 + 음식 리스트 + 말풍선 오버레이
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(154f.bhp())
        ) {
            // 배경
            Image(
                painter = painterResource(id = R.drawable.img_diet_card_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(bottomStart = 16f.bhp(), bottomEnd = 16f.bhp()))
            )

            // 음식 리스트
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 17f.wp()),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                foodList.forEach { item ->
                    Box(
                        modifier = Modifier
                            .width(76f.wp())
                            .height(135f.bhp())
                    ) {
                        // 음식 배경
                        if (item.isNightSnack) {
                            Image(
                                painter = painterResource(id = R.drawable.bg_nightsnack),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(76f.wp(), 135f.bhp())
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Image(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.size(72f.wp(), 72f.bhp())
                            )
                            Spacer(modifier = Modifier.height(6f.bhp()))
                            Text(
                                text = item.name,
                                style = DungGeunMo15,
                                fontSize = 15f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(2f.bhp()))
                            Text(
                                text = item.quantity,
                                style = DungGeunMo12,
                                fontSize = 12f.isp(),
                                color = Color(0xFF713E3A),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // 말풍선 (Row 위에 오버레이)
            if (shouldShowNightSnackTip) {
                val firstNightSnackIndex = foodList.indexOfFirst { it.isNightSnack }
                if (firstNightSnackIndex != -1) {
                    val itemSpacing = (364f.wp() - (76f.wp() * foodList.size)) / (foodList.size + 1)
                    val bubbleXOffset = itemSpacing * (firstNightSnackIndex + 1) + (76f.wp() * firstNightSnackIndex) + (76f.wp() / 2) - (185.545f.wp() / 2)

                    Image(
                        painter = painterResource(id = R.drawable.img_bubble_nightsnack),
                        contentDescription = "야식 안내",
                        modifier = Modifier
                            .absoluteOffset(
                                x = bubbleXOffset,
                                y = (-40).dp
                            )
                            .size(width = 185.545f.wp(), height = 81f.bhp())
                    )

                    LaunchedEffect(Unit) {
                        prefs.edit().putBoolean("night_snack_tip_shown", true).apply()
                    }
                }
            }
        }
    }
}




// 테스트 화면
@Composable
fun TestMealCardScreen(mealViewModel: MealViewModel) {
    val context = LocalContext.current
    var trigger by remember { mutableStateOf(0) } // 리컴포즈 트리거

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 우리가 수정한 MealCard 연결
        MealCardTestMode(
            mealType = "저녁",
            totalKcal = "390kcal",
            foodList = listOf(
                FoodItem(
                    icon = painterResource(R.drawable.ic_sweetpotato),
                    name = "고구마",
                    quantity = "0.5개",
                    isNightSnack = true // 조건 맞추기
                ),
                FoodItem(
                    icon = painterResource(R.drawable.ic_misc_foods),
                    name = "단백질\n쉐이크",
                    quantity = "300ml",
                    isNightSnack = false
                ),
                FoodItem(
                    icon = painterResource(R.drawable.ic_chickenbreast),
                    name = "닭가슴살",
                    quantity = "1조각",
                    isNightSnack = false
                )
            ),
            onEditClick = { /* 클릭 이벤트 */ },
            mealViewModel = mealViewModel
        )


        Spacer(modifier = Modifier.height(20.dp))

        // 말풍선 리셋 버튼
        Button(onClick = {
            context.getSharedPreferences("meal_prefs", Context.MODE_PRIVATE)
                .edit()
                .remove("night_snack_tip_shown")
                .apply()
            trigger++ // 화면 다시 그리기
        }) {
            Text("말풍선 리셋")
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//private fun MealCardPreview() {
//    val dummyViewModel = MealViewModel(
//        mealRepository = FakeMealRepository(),
//        foodRepository = FakeFoodRepository(),
//        context = LocalContext.current,
//        savedStateHandle = SavedStateHandle()
//    )
//
//    MealCard(
//        mealType = "아침",
//        totalKcal = "390kcal",
//        foodList = listOf(
//            FoodItem(
//                icon = painterResource(R.drawable.ic_sweetpotato),
//                name = "고구마",
//                quantity = "0.5개",
//                isNightSnack = false
//            ),
//            FoodItem(
//                icon = painterResource(R.drawable.ic_misc_foods),
//                name = "단백질\n쉐이크",
//                quantity = "300ml",
//                isNightSnack = true // 야식 예시
//            ),
//            FoodItem(
//                icon = painterResource(R.drawable.ic_chickenbreast),
//                name = "닭가슴살",
//                quantity = "0.5접시",
//                isNightSnack = false
//            ),
//            FoodItem(
//                icon = painterResource(R.drawable.ic_salad),
//                name = "단호박\n샐러드",
//                quantity = "0.5개",
//                isNightSnack = true // 야식 예시
//            )
//        ),
//        onEditClick = { /* 편집 */ },
//        mealViewModel = dummyViewModel
//    )
//}
