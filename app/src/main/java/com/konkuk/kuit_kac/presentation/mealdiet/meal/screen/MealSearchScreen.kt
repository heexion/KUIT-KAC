
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SearchBarItem
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealSearchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    foodViewModel: FoodViewModel = hiltViewModel(),
    mealViewModel: MealViewModel = hiltViewModel()
) {
    val query = foodViewModel.query
    val suggestions = foodViewModel.suggestions

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
                    label = { Text(
                        text = "무슨 음식을 먹었어?",
                        style = DungGeunMo15,
                        fontSize = 15f.isp(),
                        color = Color(0xFFB5B5B5),
                        modifier = Modifier.padding(horizontal = 20f.wp())
                    ) },
                    textStyle = DungGeunMo15.copy(
                        fontSize = 15f.isp(),
                        color = Color(0xFF000000)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12f.bhp()))

        // 3. 검색 기록 리스트
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(suggestions) { food ->
                SearchBarItem(
                    modifier = Modifier.padding(horizontal = 24f.wp()),
                    value = food.name,
                    //isLastItem = index == searchHistory.lastIndex,
                    onClick = {
                        navController.navigate("meal_search_detail/${food.name}")
                    }
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

@Preview(showBackground = true)
@Composable
private fun MealSearchScreenPreview() {
    MealSearchScreen()
}
