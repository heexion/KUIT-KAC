package com.konkuk.kuit_kac.presentation.mealdiet.diet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.*
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealItemCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import kotlin.math.roundToInt

@Composable
fun DietCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineList: List<String> = listOf("아침", "점심", "저녁"),
    dietList: List<MealResponseDto>,
    foodViewModel: FoodViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }

    var totals by remember { mutableStateOf(MacroTotals(0.0, 0.0, 0.0, 0.0)) }
    LaunchedEffect(dietList) {
        val names = dietList
            .flatMap { it.dietFoods }
            .map { it.food.name }
            .toSet()

        val localMap = mutableMapOf<String, com.konkuk.kuit_kac.local.Food>()
        for (name in names) {
            foodViewModel.getFoodByName(name)?.let { localMap[name] = it }
        }

        var kcal = 0.0
        var c = 0.0
        var p = 0.0
        var f = 0.0

        dietList.forEach { meal ->
            meal.dietFoods.forEach { dietFood ->
                val qty = (dietFood.quantity ?: 0.0).toDouble()
                val apiFood = dietFood.food
                val localFood = localMap[apiFood.name]
                val kcalPerUnit = (apiFood.calorie ?: localFood?.calorie ?: 0.0)
                kcal += kcalPerUnit * qty

                // Pull macros from local DB (adjust field names if needed)
                val carbPerUnit = (localFood?.carb ?: 0.0)
                val proteinPerUnit = (localFood?.protein ?: 0.0)
                val fatPerUnit = (localFood?.fat ?: 0.0)

                c += carbPerUnit * qty
                p += proteinPerUnit * qty
                f += fatPerUnit * qty
            }
        }

        totals = MacroTotals(kcal, c, p, f)
    }
    val macroCalTotal = (totals.carbG * 4.0) + (totals.proteinG * 4.0) + (totals.fatG * 9.0)
    fun pct(part: Double, denom: Double): Float =
        if (denom > 0.0) (((part / denom) * 100.0 * 10).roundToInt() / 10f) else 0f

    val carbPct = pct(totals.carbG * 4.0, macroCalTotal)
    val proteinPct = pct(totals.proteinG * 4.0, macroCalTotal)
    val fatPct = pct(totals.fatG * 9.0, macroCalTotal)

    val cal = totals.totalKcal.roundToInt()

    Box(modifier = modifier.width(364f.wp())) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20f.bhp()))
                .background(color = Color(0xFFFFF1AB))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Box(modifier = Modifier.padding(top = 22f.bhp(), start = 94f.wp())) {
                    Box(
                        modifier = Modifier
                            .width(176f.wp())
                            .height(28f.bhp())
                            .clip(RoundedCornerShape(7f.bhp()))
                            .background(Color(0xFFFFFCEE))
                            .noRippleClickable { expanded = true },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "아침식단1",
                            style = DungGeunMo17,
                            fontSize = 17f.isp(),
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(start = 49.5f.wp())
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dropdown),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 7f.wp())
                                .size(12f.wp(), 12f.bhp())
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(176f.wp())
                            .background(Color(0xFFFFF6C3))
                    ) {
                        routineList.forEachIndexed { index, routine ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = routine,
                                        fontSize = 16f.isp(),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                },
                                onClick = { expanded = false }
                            )
                            if (index < routineList.lastIndex) {
                                Divider(
                                    color = Color(0xFF999999),
                                    modifier = Modifier.padding(horizontal = 7f.wp())
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(start = 224f.wp())
                            .size(26.75811f.bhp(), 26.75811f.bhp())
                            .clip(RoundedCornerShape(13.27905f.bhp()))
                            .border(1.dp, Color(0xff000000), RoundedCornerShape(13.27905f.bhp()))
                            .background(Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))))
                            .noRippleClickable { navController.navigate(Route.DietPatch.route) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_diet_pen),
                            contentDescription = "pen",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.padding(top = 20f.bhp(), start = 16f.wp(), end = 15f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16f.bhp())
            ) {
                dietList.forEach { meal ->
                    meal.dietFoods.forEach { dietFood ->
                        MealItemCard(
                            foodNum = dietFood.food.id,
                            image = dietFood.food.foodType.toDrawable(),
                            foodName = dietFood.food.name,
                            foodAmount = (dietFood.quantity ?: 0.0).toFloat(),
                            foodKcal = (dietFood.food.calorie ?: 0.0).toInt(),
                            onDeleteClick = {},
                            navController = navController
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(122f.bhp())
                    .padding(top = 12f.bhp(), start = 32.56f.wp(), end = 29.68f.bhp())
            ) {
                EllipseNyam(ellipseLength = 122.0, mascotLength = 73.06644)

                Image(
                    modifier = Modifier
                        .width(197.37698f.wp())
                        .height(67.78002f.bhp())
                        .offset(y = 22.2f.bhp(), x = 104.38f.wp()),
                    painter = painterResource(R.drawable.img_home_existtextballoon),
                    contentDescription = "text balloon"
                )
                Text(
                    modifier = Modifier
                        .size(135f.wp(), 40f.bhp())
                        .offset(142.26f.wp(), 40.12f.bhp()),
                    text = "총 ${cal}kcal이야!\n식단 수준은 양호해",
                    lineHeight = 20f.isp(),
                    style = DungGeunMo15,
                    fontSize = 15f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
            DietMultipleNutritionBar(
                modifier = Modifier.padding(start = 17f.wp(), end = 15f.wp(), top = 13.29f.bhp()),
                carb = carbPct,
                protein = proteinPct,
                fat = fatPct
            )

            Box(modifier = Modifier.fillMaxWidth().height(20f.bhp()))
        }
    }
}

data class MacroTotals(
    val totalKcal: Double,
    val carbG: Double,
    val proteinG: Double,
    val fatG: Double
)
