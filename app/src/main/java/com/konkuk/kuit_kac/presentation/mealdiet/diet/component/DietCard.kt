package com.konkuk.kuit_kac.presentation.mealdiet.diet.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.component.MealItemCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun DietCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineList: List<String> = listOf("아침", "점심", "저녁"),
) {
    var expanded by remember { mutableStateOf(false) }
    val existList = listOf(
        1, 2, 3, 4
    )
    val cal = 677;

    Box(
        modifier = modifier
            .width(364f.wp())
    ) {
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
                    .padding(
                        top = 20f.bhp(),
                        start = 16f.wp(), end = 15f.wp()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16f.bhp())
            ) {
                existList.forEach { exist ->
                    MealItemCard(
                        foodNum = exist,
                        image = R.drawable.ic_dumplings,
                        foodName = "고기만두",
                        foodAmount = 1,
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
                    .padding(
                        top = 12f.bhp(), start = 32.56f.wp(),
                        end = 29.68f.bhp()
                    )
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
                    .padding(start = 17f.wp(), end = 15f.wp(), top = 13.29f.bhp()),
                carb = 65f, protein = 18f, fat = 13f
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20f.bhp())
            )

        }
//        Box(
//            modifier = Modifier
//                .align(Alignment.Center)
//                .offset(
//                    x = 182f.wp(),
//                    y = -20f.bhp()
//                )
//                .size(35f.wp(), 35f.bhp())
//                .clip(RoundedCornerShape(11f.bhp()))
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
//                    )
//                )
//                .clickable(
//                    onClick = {
//                    }
//                )
//                .border(1.dp, Color(0xFF000000), RoundedCornerShape(11f.bhp())),
//            contentAlignment = Alignment.Center) {
//            Image(
//                painter = painterResource(R.drawable.svg_all_point),
//                contentDescription = "pointer",
//                modifier = Modifier
//                    .size(9f.wp(), 13f.bhp())
//            )
//        }
    }
}

@Preview
@Composable
private fun DietCardWithButtonPreview() {
    val navController = rememberNavController()
    DietCard(navController = navController)
}