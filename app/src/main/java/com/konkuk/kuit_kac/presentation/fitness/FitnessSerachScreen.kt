package com.konkuk.kuit_kac.presentation.fitness

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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var searchHistory by remember {
        mutableStateOf(listOf("벤치프레스", "바벨 로우", "핵스쿼트", "풀업", "사이드 레터럴 레이즈", "덤벨 플라이"))
    }

    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBE8))
    ) {
        Column {
            FitnessTopBarWithSearch(
                title = "루틴 추가하기",
                placeholderText = "무슨 운동을 했어?",
                placeholderTextColor = Color(0xFF797979),
                placeholderTextStyle = DungGeunMo17,
                onBackClick = { navController.popBackStack() },
                onSearchClick = {},
                onClearClick = {}
            )

            Spacer(modifier = Modifier.height(12f.bhp()))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(searchHistory) { index, item ->
                    FitnessSearchBarItem(
                        modifier = Modifier.padding(horizontal = 27f.wp()),
                        value = item,
                        isLastItem = index == searchHistory.lastIndex,
                        onClick = {
                            selectedItem = item
                            showDialog = true
                        },
                        onDeleteClick = {
                            searchHistory = searchHistory.toMutableList().apply { removeAt(index) }
                        }
                    )
                }

            }
        }

        // 모달 다이얼로그
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
                                .clip(RoundedCornerShape(30f.wp()))
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color(0xFFFFF6C3), Color(0xFFFFA837))
                                    )
                                )
                                .border(1.dp, Color.Black, RoundedCornerShape(30f.wp()))
                                .clickable {
                                    // TODO: 추가 로직
                                    showDialog = false
                                    navController.navigate("fitness_edit")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "추가하기",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color.Black
                            )
                        }
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
