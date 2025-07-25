package com.konkuk.kuit_kac.presentation.fitness.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.SearchBarItem
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessRoutineSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var selectedItem by remember { mutableStateOf("") }
    var searchHistory by remember {
        mutableStateOf(listOf("벤치프레스", "바벨 로우", "핵스쿼트", "풀업", "사이드 레터럴 레이즈", "덤벨 플라이"))
    }
    var searchText by remember { mutableStateOf("") }

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
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "운동 루틴 추가하기",
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
                    BasicTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48f.bhp())
                            .clip(RoundedCornerShape(30f.bhp()))
                            .border(1.dp, Color.Black, RoundedCornerShape(30f.bhp()))
                            .padding(horizontal = 20f.wp()),
                        singleLine = true,
                        textStyle = DungGeunMo15.copy(fontSize = 15f.isp(), color = Color.Black),
                        keyboardActions = KeyboardActions(onDone = {
                            if (searchText.isNotBlank()) {
                                navController.navigate("fitness/detail/$searchText")
                            }
                        }),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
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
                        navController.navigate("fitness/detail/$item")
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FitnessRoutineSearchScreenPreview() {
    FitnessRoutineSearchScreen()
}
