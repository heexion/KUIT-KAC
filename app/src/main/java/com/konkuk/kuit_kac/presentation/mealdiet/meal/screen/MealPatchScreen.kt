package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.component.MealItemCard
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.DietMultipleNutritionBar
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealPatchScreen(modifier: Modifier = Modifier,
                    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    val existList = listOf(
        1,2,3,4
    )
    val cal = 677;
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(
                brush =
                    Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                        radius = 2000f
                    )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 12.51f.hp())
                .fillMaxWidth()
        ){
            Image(
                modifier = Modifier
                    .offset(x = 78f.wp())
                    .size(272f.wp(),96f.bhp()),
                painter = painterResource(R.drawable.img_diet_patchballoon),
                contentDescription = "textballoon"
            )
            EllipseNyam(
                modifier = Modifier
                    .offset(y = 72f.bhp(), x = 117f.wp()),
                ellipseLength = 177.17578, mascotLength = 106.1115
            )
            Column(
                modifier = Modifier
                    .padding(top = 256f.hp(), start = 24f.wp())
                    .width(364f.wp())
                    .clip(RoundedCornerShape(20f. bhp()))
                    .background(color = Color(0xFFFFF1AB))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22f.bhp(),
                            start = 94f.wp(), end = 94f.wp())
                        .height(28f.bhp())
                        .clip(RoundedCornerShape(7f.bhp()))
                        .background(color = Color(0xFFFFFCEE)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        modifier = Modifier,
                        text = "아침식단1",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 20f.bhp(),
                            start = 16f.wp(), end = 15f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16f.bhp())
                ) {
                    existList.forEach{exist->
                        MealItemCard(
                            foodNum = exist,
                            image = R.drawable.ic_dumplings,
                            foodName = "고기만두",
                            foodAmount = 1,
                            foodKcal = 120,
                            onDeleteClick = { }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(19f.bhp())
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(122f.bhp())
                .padding(top = 12f.bhp(), start = 56.58f.wp())
        ){
            EllipseNyam(ellipseLength = 122.0, mascotLength = 73.06644)
            Image(
                modifier = Modifier
                    .width(197.37698f.wp())
                    .height(67.78002f.bhp())
                    .offset(y = 26.84f.bhp(), x = 104.38f.wp()),
                painter = painterResource(R.drawable.img_home_existtextballoon),
                contentDescription = "text balloon"
            )
            Text(
                modifier = Modifier
                    .size(135f.wp(),40f.bhp())
                    .offset(142.26f.wp(),40.12f.bhp()),
                text = "총 "+ cal +"kcal이야!\n식단 수준은 양호해",
                lineHeight = 20f.isp(),
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )
        }
        DietMultipleNutritionBar(
            modifier = Modifier
                .padding(start = 41f.wp(), end = 39f.wp(), top = 13.29f.bhp()),
            carb = 65f, protein = 18f, fat = 13f
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32f.bhp(),
                    start = 17f.wp(), end = 15f.wp())
                .height(70f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(
                    brush = Brush
                        .verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF),Color(0xFFFFB638))
                        )
                )
                .border(2.dp, Color(0xFF000000),RoundedCornerShape(20f.bhp()))
                .clickable { navController.navigate("meal_edit_result") },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "저장하기",
                style = DungGeunMo20,
                textAlign = TextAlign.Center,
                fontSize = 20f.isp(),
                color = Color(0xFF000000)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(115f.bhp())
                .background(Color.Transparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MealPatchScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MealPatchScreen(navController=navController)
}