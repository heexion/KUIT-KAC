package com.konkuk.kuit_kac.presentation.fitness.screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SearchBarItem
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessTopBarWithSearch
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedItem by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var searchHistory by remember {
        mutableStateOf(listOf("벤치프레스", "바벨 로우", "핵스쿼트", "풀업", "사이드 레터럴 레이즈", "덤벨 플라이"))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBE8))
    ) {
        // 1 + 2. 상단바 + 검색바 통합 박스
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 50f.wp(),
                        bottomEnd = 50f.wp()
                    )
                )
                .background(Color(0xFFFFF1AB))
                .padding(top = 18f.hp(), bottom = 22f.bhp(), start = 24f.wp(), end = 24f.wp())
        ) {
            Column {
                // 상단바
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {


                    // 타이틀
                    Text(
                        text = "루틴 추가하기",
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
                        .border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(30f.bhp())),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "무슨 운동을 했어?",
                        style = DungGeunMo15,
                        fontSize = 15f.isp(),
                        color = Color(0xFFB5B5B5),
                        modifier = Modifier.padding(horizontal = 20f.wp())
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12f.bhp()))

        // 3. 검색 기록 리스트
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(searchHistory) { index, item ->
                SearchBarItem(
                    modifier = Modifier.padding(horizontal = 27f.wp()),
                    value = item,
                    isLastItem = index == searchHistory.lastIndex,
                    onClick = {
                        selectedItem = item
                        showDialog = true
                    }
                )
            }
        }
    }
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .width(320f.wp())
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFEF9EC))
                    .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                    .padding(vertical = 32f.bhp(), horizontal = 24f.wp())
            ) {
                // 닫기 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "닫기",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        // .padding(top = 4.dp, end = 4.dp)
                        .size(20.dp)
                        .clickable { showDialog = false }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "‘$selectedItem’",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "루틴에 추가할까요?",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000)
                    )
                    Spacer(modifier = Modifier.height(24f.bhp()))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48f.bhp())
                            .clip(RoundedCornerShape(30f.bhp()))
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFFFFF6C3), Color(0xFFFFA837))
                                )
                            )
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(30f.bhp()))
                            .clickable {
                                // TODO: 추가 로직
                                showDialog = false
                                navController.navigate(route = Route.FitnessEdit.route)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "추가하기",
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

@Composable
fun FitnessSearchBarItem(
    modifier: Modifier = Modifier,
    value: String,
    isLastItem: Boolean = false,
    isCloseIconExist: Boolean = true,
    onClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {} // 새로 추가
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() } //  Row 전체 클릭 가능
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = DungGeunMo20,
                color = Color(0xFF000000),
            )

            if (isCloseIconExist)
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "삭제 아이콘",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onDeleteClick() } //  삭제 이벤트 따로 처리
                )
        }

        if (!isLastItem) {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color(0x3B000000))
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FitnessSearchScreenPreview() {
    FitnessSearchScreen()
}
