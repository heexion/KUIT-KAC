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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealState
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealCard
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealFastingCard
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealRecordCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

// 데이터 클래스
data class MealCardData(
    val mealType: String,
    val totalKcal: String,
    val foodList: List<Triple<Painter, String, String>>,
    val isPlanned: Boolean = false // 자동 기록 여부
)

@Composable
fun MealMainScreen(
    navController: NavHostController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    mealCards: List<MealCardData>,
    onRecordClick: () -> Unit = {},
    onFastedClick: () -> Unit = {}
) {
    val mealTypeList = listOf("아침", "점심", "저녁", "간식")
    var showDialog by remember { mutableStateOf(false) }
    var dialogMealType by remember { mutableStateOf("") }
    var showAutoRecordDialog by remember { mutableStateOf(false) }

    val initialStates = remember {
        mealTypeList.map { type ->
            val matched = mealCards.find { it.mealType == type }
            MealState(
                mealType = type,
                mealCardData = matched,
                isFasting = false
            )
        }
    }
    val mealStates = remember { mutableStateListOf<MealState>().apply { addAll(initialStates) } }

    LaunchedEffect(Unit) {
        val isAllBlank = mealStates.all { it.mealCardData == null && !it.isFasting }
        val hasPlan = mealCards.isNotEmpty()
        if (isAllBlank && hasPlan) {
            showAutoRecordDialog = true
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFF1AB))
                .padding(start = 24f.wp(), end = 24f.wp(), top = 16f.hp(), bottom = 14.5f.bhp())
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_diet),
                        contentDescription = "식단 아이콘",
                        modifier = Modifier.size(28.8584f.wp(), 28.8584f.bhp())
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFF3C1), Color(0xFFFFFFFF), Color(0xFFFFF3C1))
                    )
                )
                .padding(horizontal = 24f.wp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(28f.bhp()))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                mealStates.forEachIndexed { index, state ->
                    val label = mealTypeList.getOrElse(index) { "기타" }

                    when {
                        state.mealCardData != null -> {
                            MealCard(
                                mealType = label,
                                totalKcal = state.mealCardData.totalKcal,
                                foodList = state.mealCardData.foodList,
                                onEditClick = { navController.navigate(Route.MealPatch.route) }
                            )
                        }
                        state.isFasting -> {
                            MealFastingCard(
                                mealType = label,
                                navController = navController,
                                onCloseClick = {
                                    dialogMealType = label
                                    showDialog = true
                                }
                            )
                        }
                        else -> {
                            MealRecordCard(
                                mealType = label,
                                navController = navController,
                                onFastingClick = {
                                    mealStates[index] = state.copy(isFasting = true)
                                }
                            )
                        }
                    }
                }
            }
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .width(364f.wp())
                        .height(202.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFFFEF9EC))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                        .padding(vertical = 32f.bhp(), horizontal = 24f.wp())
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "닫기",
                        tint = Color(0xFF000000),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(20.dp)
                            .clickable { showDialog = false }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = "단식 기록을 삭제할까?",
                            style = DungGeunMo17,
                            fontSize = 17f.isp(),
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(24f.bhp()))
                        Box(
                            modifier = Modifier
                                .width(284.343.dp)
                                .height(56.dp)
                                .clip(RoundedCornerShape(35.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFFFFF6C3), Color(0xFFFFA837))
                                    )
                                )
                                .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                                .clickable {
                                    val idx = mealTypeList.indexOf(dialogMealType)
                                    if (idx != -1) {
                                        mealStates[idx] = mealStates[idx].copy(isFasting = false)
                                    }
                                    showDialog = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "삭제하기",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        }
                    }
                }
            }
        }

        if (showAutoRecordDialog) {
            Dialog(onDismissRequest = { showAutoRecordDialog = false }) {
                Box(
                    modifier = Modifier
                        .width(364f.wp())
                        .height(202.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFFFEF9EC))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                        .padding(vertical = 32f.bhp(), horizontal = 24f.wp())
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "닫기",
                        tint = Color(0xFF000000),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(20.dp)
                            .clickable { showAutoRecordDialog = false }
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = "오늘 계획된 식단이 있어!\n그대로 기록해줄까?",
                            style = DungGeunMo17,
                            fontSize = 17f.isp(),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24f.bhp()))
                        Box(
                            modifier = Modifier
                                .width(284.dp)
                                .height(56.dp)
                                .clip(RoundedCornerShape(35.dp))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFFFFF6C3), Color(0xFFFFA837))
                                    )
                                )
                                .border(1.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                                .clickable {
                                    mealCards.forEach { card ->
                                        val index = mealTypeList.indexOf(card.mealType)
                                        if (index != -1) {
                                            mealStates[index] = mealStates[index].copy(mealCardData = card)
                                        }
                                    }
                                    showAutoRecordDialog = false
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "그대로 기록하기",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        }
                    }
                }
            }
        }
    }
}



//@Composable
//fun CalendarFloatingButton(
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = Modifier
//            .size(61.dp)
//            .clip(CircleShape)
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color(0xFFFFFFFF), // 위쪽 흰색
//                        Color(0xFFFFB638)  // 아래쪽 주황색
//                    )
//                )
//            )
//            .border(1.dp, Color.Black, shape = CircleShape)
//            .shadow(elevation = 4.dp, shape = CircleShape, ambientColor = Color.Black.copy(alpha = 0.1f))
//            .clickable { onClick() },
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_calendar),
//            contentDescription = "Calendar"
//        )
//    }
//}
//
//@Composable
//fun SpeechBubble(messageText: String,
//                 modifier: Modifier) {
//    Box(
//        modifier = modifier
//            .width(219f.wp())
//            .height(83f.bhp()),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
//            contentDescription = null,
//            modifier = Modifier.matchParentSize()  // 말풍선 전체 크기 채움
//        )
//        Text(
//            text = messageText,
//            style = DungGeunMo20.copy(fontSize = 20f.isp()),
//            lineHeight = 28.sp,
//            color = Color(0xFF000000),
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(bottom = 21f.bhp())
//        )
//    }
//}
//

//@Composable
//fun OutlinedRoundedButton(
//    modifier: Modifier,
//    value: String,
//    onClick: () -> Unit
//) {
//    Box(
//        modifier = modifier
//            .width(125f.wp())
//            .height(48f.bhp())
//            .clip(RoundedCornerShape(20f.bhp()))
//            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
//            .clickable { onClick() },
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            modifier = Modifier
//                .matchParentSize()
//                .clip(RoundedCornerShape(20f.bhp())),
//            painter = painterResource(id = R.drawable.bg_orange_button_default),
//            contentDescription = "bg",
//            contentScale = ContentScale.FillBounds
//        )
//        Text(
//            text = value,
//            style = DungGeunMo20,
//            fontSize = 20f.isp(),
//            color = Color(0xFF000000)
//        )
//    }
//}

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


////식단 있을때
//@Preview(showBackground = true)
//@Composable
//private fun MealMainScreenPreview() {
//    val navController = rememberNavController()
//
//    MealMainScreen(
//        navController = navController,
//        selectedTab = "기록",
//        onTabClick = {},
//        onRecordClick = {},
//        onFastedClick = {},
//        mealCards = listOf(
//            MealCardData(
//                mealType = "아침",
//                totalKcal = "420kcal",
//                foodList = listOf(
//                    Triple(painterResource(id = R.drawable.ic_sweetpotato), "고구마", "100g"),
//                    Triple(painterResource(id = R.drawable.ic_salad), "샐러드", "50g"),
//                    Triple(painterResource(id = R.drawable.ic_chickenbreast), "닭가슴살", "80g")
//                )
//            )
//        )
//    )
//}

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

@Preview(showBackground = true)
@Composable
private fun MealMainScreenPreview() {
    val navController = rememberNavController()
    MealMainScreen(
        navController = navController,
        selectedTab = "기록",
        onTabClick = {},
        mealCards = emptyList()
    )
}
