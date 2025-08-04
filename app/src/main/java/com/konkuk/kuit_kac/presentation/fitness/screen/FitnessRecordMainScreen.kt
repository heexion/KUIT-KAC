package com.konkuk.kuit_kac.presentation.fitness.screen

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessItemData
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessRecordCard
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20


@Composable
fun FitnessRecordMainScreen(
    navController: NavHostController,
    selectedTab: String,
    onTabClick: (String) -> Unit,
    fitnessItems: List<FitnessItemData>
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // 상단 배경
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFF1AB))
                    .padding(start = 20f.wp(), end = 20f.wp(), top = 16f.hp(), bottom = 11f.bhp())
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 타이틀
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_fitness),
                            contentDescription = "운동 아이콘",
                            modifier = Modifier.size(20f.wp())
                        )
                        Spacer(modifier = Modifier.width(4f.wp()))
                        Text(
                            text = "운동",
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
                            value = "운동 기록",
                            isSelected = selectedTab == "기록",
                            buttonHeight = 49,
                            onClick = { onTabClick("기록") }
                        )
                        Spacer(modifier = Modifier.width(16f.wp()))
                        SelectButton(
                            modifier = Modifier.weight(1f),
                            value = "나만의 운동 루틴",
                            isSelected = selectedTab == "나만의",
                            buttonHeight = 49,
                            onClick = {
                                navController.navigate(route = Route.Fitness.route)
                                onTabClick("나만의")
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF000000))
            )

            // 운동 기록이 있을 때 / 없을 때 분기
            if (fitnessItems.isEmpty()) {
                //운동 기록이 없을 때

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
                        .padding(horizontal = 20f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(28f.bhp()))
                    RecordFitnessMainButton(
                        onClick = { navController.navigate(route = Route.FitnessCreate.route) }
                    )
                    Spacer(modifier = Modifier.height(25f.bhp()))
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

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 88f.bhp()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                   // .offset(x = 70.31f.wp())
                                    .size(272f.wp(),96f.bhp()),
                                painter = painterResource(R.drawable.img_fitnessrecordmain_text),
                                contentDescription = "textballoon"
                            )
//                            EllipseNyam(
//                                ellipseLength = 182.0,
//                                mascotLength = 109.0,
//                                modifier = Modifier.clickable {
//                                    navController.navigate(Route.FitnessExist.route)
//                                }
//                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                HamcoachGif(
                                    num = 1,
                                    ellipseLength = 182.0,
                                    mascotLength = 160.0,
                                )
                            }
                        }
                    }
                }
            } else {
                // 운동 기록이 있을 때
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
                        .padding(horizontal = 20f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(28f.bhp()))

                    FitnessRecordCard(
                        title = "오늘의 운동!",
                        fitnessItems = fitnessItems,
                        onEditClick = {},
                        navController = navController,
                        isEditable = false
                    )
                }
            }
        }

        // 플로팅 버튼 (캘린더)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-25f.wp()), y = (-93f.bhp()))
                .size(61f.wp())
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
                modifier = Modifier.size(42f.wp())
            )
        }
    }
}
@Composable
fun RecordFitnessMainButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(364f.wp())
            .height(49f.bhp())
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(35f.wp())
            )
            .clickable { onClick() }
            .padding(horizontal = 16f.wp()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_record ),
            contentDescription = "운동 기록 아이콘",
            modifier = Modifier.size(27f.wp())
        )
        Spacer(modifier = Modifier.width(8f.wp()))
        Text(
            text = "운동 기록하기",
            style = DungGeunMo20.copy(fontSize = 17f.isp()),
            color = Color(0xFF000000)
        )
    }
}


//기록 없을때 프리뷰
@Preview(showBackground = true)
@Composable
fun FitnessRecordMainScreenPreview_Empty() {
    val navController = rememberNavController()

    FitnessRecordMainScreen(
        navController = navController,
        selectedTab = "기록",
        onTabClick = {},
        fitnessItems = emptyList() //  기록 없음
    )
}

////기록 있을때 프리뷰
//@Preview(showBackground = true)
//@Composable
//fun PreviewFitnessRecordMainScreen() {
//    val navController = rememberNavController()
//    var selectedTab by remember { mutableStateOf("기록") }
//
//    val sampleFitnessItems = listOf(
//        FitnessItemData(
//            imageRes = R.drawable.ic_lowerbody,
//            name = "레그 컬",
//            setCount = 2,
//            kcal = 120,
//            onDeleteClick = {}
//        ),
//        FitnessItemData(
//            imageRes = R.drawable.ic_lowerbody,
//            name = "레그 프레스",
//            setCount = 2,
//            kcal = 120,
//            onDeleteClick = {}
//        ),
//        FitnessItemData(
//            imageRes = R.drawable.ic_lowerbody,
//            name = "레그 익스텐션",
//            setCount = 2,
//            kcal = 120,
//            onDeleteClick = {}
//        )
//    )
//
//    FitnessRecordMainScreen(
//        navController = navController,
//        selectedTab = selectedTab,
//        onTabClick = { selectedTab = it },
//        fitnessItems = sampleFitnessItems // ← 주입 필수
//    )
//}

