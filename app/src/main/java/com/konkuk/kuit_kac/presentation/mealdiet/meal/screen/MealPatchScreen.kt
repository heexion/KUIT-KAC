package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen

import android.graphics.PathEffect
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealItemCard
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.DietMultipleNutritionBar
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow

@Composable
fun MealPatchScreen(modifier: Modifier = Modifier,
                    navController: NavHostController,
                    routineList:List<String> = listOf("아침", "점심", "저녁")
) {
    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val lazyState = rememberLazyListState()
    val existList = listOf(
        1,2,3,4,5,6,7,8
    )
    val cal = 677;
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(165f.bhp())
                .background(color = deepYellow)
                .border(1.dp, Color(0xFF000000))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16f.hp()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(28.8584f.wp(),28.8584f.bhp()),
                    painter = painterResource(R.drawable.ic_alcohol),
                    contentDescription = "utensils"
                )
                Text(
                    text = "식단",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 17f.bhp(),
                        start = 24f.wp(), end = 24f.wp()),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp())
                        .clickable {
                            navController.navigate(Route.DietCreate.route)
                        },
                    value = "식단기록"
                )
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp()),
                    value = "나만의 식단"
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush =
                    Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                        radius = 2000f
                    )
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                EllipseNyam(ellipseLength = 137.54, mascotLength = 82.37,
                    modifier = Modifier
                        .offset(134f.wp(),94f.bhp()))
                Image(
                    painter = painterResource(R.drawable.img_diet_maintextballoon),
                    contentDescription = "text balloon",
                    modifier = Modifier
                        .offset(66f.wp(),25.3f.bhp())
                        .size(282f.wp(),110f.bhp())
                )
                Text(
                    modifier = Modifier
                        .offset(79.2f.wp(),52.6f.bhp()),
                    text = "너만의 식단들이야!\n어떤 식단을 진행했는지 골라줘!",
                    textAlign = TextAlign.Center,
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 106f.bhp(),
                        start = 24f.wp())
                    .width(364f.wp())
                    .clip(RoundedCornerShape(20f.bhp()))
                    .background(color = Color(0xFFFFF1AB))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())),
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 22f.bhp(), start = 94f.wp())
                    ) {
                        Box(
                            modifier = Modifier
                                .width(176f.wp())
                                .height(28f.bhp())
                                .clip(RoundedCornerShape(7f.bhp()))
                                .background(Color(0xFFFFFCEE))
                                .clickable { expanded = true },
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "아침식단1",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .padding(start = 49.5f.wp())
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
                                    onClick = {
                                        expanded = false
                                    }
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
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                                    )
                                )
                                .clickable {
                                    navController.navigate(Route.DietPatch.route)
                                }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_diet_pen),
                                contentDescription = "pen",
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }
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
                            foodAmount = 1f,
                            foodKcal = 120,
                            onDeleteClick = { },
                            navController = navController
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(122f.bhp())
                        .padding(top = 12f.bhp(), start = 32.56f.wp(),
                            end = 29.68f.bhp())
                ){
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
                        .padding(start = 17f.wp(), end = 15f.wp(), top = 13.29f.bhp()),
                    carb = 65f, protein = 18f, fat = 13f
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20f.bhp())
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
}

@Preview(showBackground = true)
@Composable
fun MealPatchScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    MealPatchScreen(navController=navController)
}