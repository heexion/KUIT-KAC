package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.component.SearchBarItem
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealEditSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    foodViewModel: FoodViewModel = hiltViewModel(),
    mealViewModel: MealViewModel = hiltViewModel()
) {
    val query = foodViewModel.query
    val suggestions = foodViewModel.suggestions
    val focusManager = LocalFocusManager.current // 포커스 매니저

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBE8))
            // 바깥 터치 시 포커스 해제
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
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
                    Text(
                        text = "식단 검색하기",
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF713E3A),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(23f.bhp()))

                // 검색 바
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 72f.bhp()),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF000000),
                        unfocusedBorderColor = Color(0xFF000000),
                        cursorColor = Color(0xFF000000)
                    ),
                    shape = RoundedCornerShape(30f.bhp()),
                    singleLine = true,
                    value = query,
                    onValueChange = { foodViewModel.onQueryChange(it) },
                    label = {
                        Text(
                            text = "무슨 음식을 먹었어?",
                            style = DungGeunMo15,
                            fontSize = 15f.isp(),
                            color = Color(0xFFB5B5B5),
                            modifier = Modifier.padding(horizontal = 20f.wp())
                        )
                    },
                    textStyle = DungGeunMo15.copy(
                        fontSize = 15f.isp(),
                        color = Color(0xFF000000)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() } // Done 키 입력 시 포커스 해제
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12f.bhp()))

        // 3. 검색 기록 리스트
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(suggestions) { food ->
                SearchBarItem(
                    modifier = Modifier
                        .padding(horizontal = 24f.wp())
                        .clickable {

                        },
                    onClick = {
                        focusManager.clearFocus() // 아이템 클릭 시 포커스 해제
                        navController.navigate("meal_edit_search_detail/${food.name}")
                    },
                    value = food.name
                )
            }
        }
    }

    Spacer(
        modifier = Modifier
            .padding(bottom = 120f.bhp())
            .background(Color(0xFFFFFBE8))
    )
}
