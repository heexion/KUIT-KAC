package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessItemCard
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20


@Composable
fun FitnessDetailRecordAddScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    // 운동 데이터 예시 (todo: ViewModel로 수정 필요)
    val fitnessList = listOf(
        FitnessDetailData(
            id = 1,
            imageRes = R.drawable.ic_lowerbody,
            name = "레그 프레스",
            amount = 3,
            kcal = 120,
            onDeleteClick = { /* 삭제 처리 */ }
        )
    )
    val totalKcal = fitnessList.sumOf { it.kcal }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        // 상단 타이틀 + 말풍선
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20f.bhp())
                    .align(Alignment.TopCenter)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 137.82f.bhp()),
                    num = 1,
                    ellipseLength = 145.62891,
                    mascotLength = 110.0,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 59f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    modifier = Modifier.size(332f.wp(), 103f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = "지금까지 입력한\n오늘 운동량이야!",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 22f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 28f.bhp())
                )
            }
//            Spacer(modifier = Modifier.height(25.7f.bhp()))
            Spacer(modifier = Modifier.height(257f.bhp()))

//            EllipseNyam(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 112.12f.bhp()),
//                mascotLength = 87.70016,
//                ellipseLength = 145.62891
//            )


        }

        Spacer(modifier = Modifier.height(24f.bhp()))

        FitnessDetailCard(
            fitnessList = fitnessList,
            modifier = Modifier.padding(horizontal = 20f.wp()),
            navController = navController
        )

        Spacer(modifier = Modifier.height(27f.bhp()))

        // 구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5f.bhp())
                .padding(horizontal = 24f.wp())
                .background(Color(0xFF000000))
        )

        Spacer(modifier = Modifier.height(24f.bhp()))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24f.wp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_kcal_bar),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(364f.wp())
                    .height(58.89f.bhp())
                    .clip(RoundedCornerShape(20f.bhp()))
                    .border(1.5.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
            )

            Row(
                modifier = Modifier
                    .width(364f.wp())
                    .height(58.89f.bhp())
                    .padding(horizontal = 16f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "최종 예상 소모 칼로리",
                    style = DungGeunMo17,
                    color = Color(0xFF000000)
                )

                Text(
                    text = "$totalKcal Kcal",
                    style = DungGeunMo20,
                    color = Color(0xFF713E3A)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.1f.bhp()))

        RecordButton(
            value = "기록하기",
            onClick = {
                navController.navigate(Route.FitnessRecordResult.route)
            }
        )
    }
}
data class FitnessDetailData(
    val id: Int,
    val imageRes: Int,
    val name: String,
    val amount: Int,
    val kcal: Int,
    val onDeleteClick: () -> Unit
)

@Composable
fun FitnessDetailCard(
    modifier: Modifier = Modifier,
    fitnessList: List<FitnessDetailData>,
    navController: NavHostController
) {
    LazyColumn(
        modifier = modifier
            .width(364f.wp())
            .height(458f.bhp())
            .fillMaxWidth()
            .clip(RoundedCornerShape(20f.bhp()))
            .background(Color(0xFFFFF1AB))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
        contentPadding = PaddingValues(bottom = 24f.bhp()),
        verticalArrangement = Arrangement.spacedBy(16f.bhp())
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 22f.bhp())
                        .width(176f.wp())
                        .height(28f.bhp())
                        .clip(RoundedCornerShape(7f.bhp()))
                        .background(Color(0xFFFFFCEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "오늘의 운동",
                        textAlign = TextAlign.Center,
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8f.wp())
                    )
                }
            }
        }

        // 운동 리스트
        itemsIndexed(fitnessList) { index, item ->
            val isLastItem = index == fitnessList.lastIndex
            val bottomPadding = if (isLastItem) 16.dp else 0.dp // 유지

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16f.wp(),
                        end = 15f.wp(),
                        bottom = bottomPadding
                    )
            ) {
                FitnessItemCard(
                    FitnessNum = item.id,
                    image = item.imageRes,
                    FitnessName = item.name,
                    FitnessAmount = item.amount,
                    FitnessKcal = item.kcal,
                    onDeleteClick = item.onDeleteClick,
                    isEditable = false
                )
            }
        }

        // "운동 추가하기"
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16f.wp(), end = 15f.wp())
                    .height(84f.bhp())
                    .clip(RoundedCornerShape(15f.bhp()))
                    .background(color = Color(0xFFFFFFFF))
                    .noRippleClickable {
                        navController.navigate(Route.FitnessSearch.route)
                    }
                    .drawBehind {
                        val strokeWidth = 3.dp.toPx()
                        val pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(4.dp.toPx(), 4.dp.toPx()), 0f
                        )
                        drawRoundRect(
                            color = Color(0xFF000000),
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



@Preview
@Composable
private fun FitnessDetailRecordAddScreenPreview() {
    val navController = rememberNavController()
    FitnessDetailRecordAddScreen(navController = navController)
}