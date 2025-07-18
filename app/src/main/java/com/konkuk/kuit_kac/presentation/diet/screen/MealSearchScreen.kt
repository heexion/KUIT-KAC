package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SearchBarItem
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    // 샘플 검색 기록 상태
    var searchHistory by remember {
        mutableStateOf(listOf("마라탕", "파스타", "족발", "치즈버거", "감자탕", "짜장면"))
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
                        bottomStart = 50.dp,
                        bottomEnd = 50.dp
                    )
                )
                .background(Color(0xFFFFF1AB))
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column {
                // 상단바
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 뒤로가기 아이콘
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_arow),
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(24.dp)
                            .clickable { navController.popBackStack() }
                    )


                    // 타이틀
                    Text(
                        text = "식단 검색하기",
                        style = DungGeunMo20,
                        color = Color(0xFF713E3A),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(23.dp))

                // 검색 바
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                        .border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "무슨 음식을 먹었어?",
                        style = DungGeunMo15,
                        color = Color(0xFFB5B5B5),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. 검색 기록 리스트
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(searchHistory) { index, item ->
                SearchBarItem(
                    modifier = Modifier.padding(horizontal = 27.dp),
                    value = item,
                    isLastItem = index == searchHistory.lastIndex,
                    onClick = {
                        navController.navigate("meal_search_detail/${item}")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealSearchScreenPreview() {
    MealSearchScreen()
}
