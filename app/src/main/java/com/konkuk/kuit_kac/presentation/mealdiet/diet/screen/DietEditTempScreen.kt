package com.konkuk.kuit_kac.presentation.mealdiet.diet.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.toDrawable
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.DietMultipleNutritionBar
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel.DietViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealItemCard
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun DietEditTempScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dietViewModel: DietViewModel = hiltViewModel()
) {
    Log.d("DietEdittemp", "${dietViewModel.dietId} ${dietViewModel.dietName}")
    val originalName by dietViewModel.dietName
    var name by remember(originalName) { mutableStateOf(originalName ?: "") }
    val dietId = dietViewModel.dietId.value
    val selectedFoods = dietViewModel.selectedFoods
    if (dietId == null || selectedFoods.isEmpty()) {
        Log.d("MealEditTempScreen", "Waiting for data... dietId=$dietId, foods=$selectedFoods")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
        return
    }
    Log.d("MealEditTempScreen", "Composable launched")
    Log.d("MealEditTempScreen", "dietId=${dietId}, fwqRaw=${selectedFoods}")

    val prevRoute = navController.previousBackStackEntry?.destination?.route
    var Clicked = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val cal = selectedFoods.sumOf { (it.food.calorie * it.quantity).toInt() }


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
        ) {
            Image(
                modifier = Modifier
                    .offset(x = 78f.wp())
                    .size(272f.wp(), 96f.bhp()),
                painter = painterResource(R.drawable.img_diet_patchballoon),
                contentDescription = "textballoon"
            )
//            EllipseNyam(
//                modifier = Modifier
//                    .offset(y = 72f.bhp(), x = 117f.wp()),
//                ellipseLength = 177.17578, mascotLength = 106.1115
//            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 72f.bhp()),
                    num = 1,
                    ellipseLength = 177.17575,
                    mascotLength = 145.0,
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 256f.hp(), start = 24f.wp())
                    .width(364f.wp())
            ) {
                Column(
                    modifier = Modifier
                        .width(364f.wp())
                        .clip(RoundedCornerShape(20f.bhp()))
                        .background(color = Color(0xFFFFF1AB))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 22f.bhp(),
                                start = 94f.wp(), end = 94f.wp()
                            )
                            .height(56f.bhp())
                            .clip(RoundedCornerShape(7f.bhp()))
                            .background(color = Color(0xFFFFFCEE)),
                        contentAlignment = Alignment.Center
                    ) {
                        TextField(
                            modifier = Modifier
                                .width(176f.wp())
                                .heightIn(min=56f.bhp()),
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            placeholder = { Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ){Text(
                                text = originalName?:"제목을 입력해주세요",
                                textAlign = TextAlign.Center,
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF999999)
                            )} },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            ),
                            textStyle = DungGeunMo17.copy(
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                top = 20f.bhp(),
                                start = 16f.wp(), end = 15f.wp()
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16f.bhp())
                    ) {
                        selectedFoods.forEach { foodWithQuantity ->
                            val food = foodWithQuantity.food
                            val quantity = foodWithQuantity.quantity
                            MealItemCard(
                                foodNum = 1,
                                image = food.foodType.toDrawable(),
                                foodName = food.name,
                                foodAmount = quantity,
                                foodKcal = food.calorie.toInt(),
                                onDeleteClick = {
                                    dietViewModel.removeFood(foodWithQuantity)
                                },
                                navController = navController
                            )
                            Log.d("DietTemp", "foodType: ${food.foodType}")
                            Log.d("DietTemp", "foodType: ${food.foodType.toDrawable()}")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16f.bhp(), start = 16f.wp(), end = 15f.wp())
                            .height(84f.bhp())
                            .clip(RoundedCornerShape(15.dp))
                            .background(color = Color(0xFFFFFFFF))
                            .clickable(
                                onClick = {
                                    navController.navigate(Route.FitnessSearch.route)
                                }
                            )
                            .drawBehind {
                                val strokeWidth = 3.dp.toPx()
                                val pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                                    floatArrayOf(
                                        4.dp.toPx(),
                                        4.dp.toPx()
                                    ), 0f
                                )
                                val rect = Rect(0f, 0f, size.width, size.height)

                                drawRoundRect(
                                    color = Color(0xFF000000),
                                    style = Stroke(width = strokeWidth, pathEffect = pathEffect),
                                    size = size,
                                    cornerRadius = CornerRadius(15.dp.toPx())
                                )
                            }
                            .clickable (
                                onClick = {
                                    dietViewModel.setName(name)
                                    navController.navigate("dieteditsearch")
                                }
                            ),
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
                            text = "식단 추가하기",
                            style = DungGeunMo15,
                            fontSize = 15f.isp(),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(19f.bhp())
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(122f.bhp())
                .padding(top = 12f.bhp(), start = 56.58f.wp())
        ) {
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
                    .size(135f.wp(), 40f.bhp())
                    .offset(142.26f.wp(), 40.12f.bhp()),
                text = "총 " + cal + "kcal이야!\n식단 수준은 양호해",
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
            carb = dietViewModel.totalCarb.toFloat(), protein = dietViewModel.totalProtein.toFloat(),
            fat = dietViewModel.totalFat.toFloat()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 32f.bhp(),
                    start = 17f.wp(), end = 15f.wp()
                )
                .height(70f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(
                    brush = Brush
                        .verticalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                        )
                )
                .border(2.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                .clickable {
                    if (prevRoute == "plan_ai_detail" || prevRoute == "plan_in_person_add")
                        navController.popBackStack()
                    else
                        dietViewModel.editDiet()
                        navController.navigate(Route.MealFastingResult.route)
                },
            contentAlignment = Alignment.Center
        ) {
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