package com.konkuk.kuit_kac.presentation.mealdiet.meal.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.SelectButton2
import com.konkuk.kuit_kac.presentation.mealdiet.diet.component.viewmodel.DietViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.diet.screen.DietSwipeCardPager
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodViewModel
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@Composable
fun MealPatchScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    routineList: List<String> = listOf("아침", "점심", "저녁"),
    dietViewModel: DietViewModel = hiltViewModel(),
    mealViewModel: MealViewModel = hiltViewModel(),
    foodViewModel: FoodViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val lazyState = rememberLazyListState()
    LaunchedEffect(Unit) {
        dietViewModel.getDiet()
    }

    val dietList = dietViewModel.getDietState.value
    val state = !dietList.isNullOrEmpty()



    val cal = 677;
    val resolvedFoods = remember { mutableStateOf<List<FoodWithQuantity>>(emptyList()) }
    Log.d("MealPatch", "${dietList}")
    val pageCount = dietList?.size ?: 1
    val pagerState = rememberPagerState(pageCount = { pageCount })
    val currentPage = pagerState.currentPage
//    val currentDiet = dietList?.get(currentPage)

    LaunchedEffect(currentPage, dietList) {
        if (!dietList.isNullOrEmpty() && currentPage in dietList.indices) {
            val selectedDiet = dietList[currentPage]
            val result = coroutineScope {
                selectedDiet.dietFoods.map { df ->
                    async {
                        val name = df.food.name
                        val quantity = df.quantity?.toFloat()
                        val food = foodViewModel.getFoodByName(name)
                        if (food != null) FoodWithQuantity(food, quantity ?: 1f) else null
                    }
                }.awaitAll().filterNotNull()
            }
            resolvedFoods.value = result
        }
    }
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
                        .size(28.8584f.wp(), 28.8584f.bhp()),
                    painter = painterResource(R.drawable.ic_diet),
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
                    .padding(
                        top = 17f.bhp(),
                        start = 24f.wp(), end = 24f.wp()
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SelectButton2(
                    modifier = Modifier
                        .size(174f.wp(), 49f.bhp())
                        .noRippleClickable {
                            navController.navigate(Route.DietCreate.route)
                        },
                    value = "식단 기록",
                    isSelected = true
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
                .background(
                    brush =
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                            radius = 2000f
                        )
                ),
        ) {
            if (state) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 94f.bhp()),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        HamcoachGif(
                            num = 1,
                            ellipseLength = 137.54,
                            mascotLength = 112.0,
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.img_diet_maintextballoon),
                        contentDescription = "text balloon",
                        modifier = Modifier
                            .offset(66f.wp(), 25.3f.bhp())
                            .size(282f.wp(), 110f.bhp())
                    )
                    Text(
                        modifier = Modifier
                            .offset(79.2f.wp(), 52.6f.bhp()),
                        text = "너만의 식단들이야!\n어떤 식단을 진행했는지 골라줘!",
                        textAlign = TextAlign.Center,
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000)
                    )
                }
            }

            if (state) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 108f.bhp())
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                                radius = 2000f
                            )
                        ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DietSwipeCardPager(
                            navController = navController,
                            modifier = Modifier.padding(start = 24f.wp()),
                            dietList = dietList ?: emptyList(),
                            pagerState = pagerState
                        )
                    }
                    Spacer(modifier = Modifier.height(38f.bhp()))
                    DefaultButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24f.wp()),
                        onClick = {
                            val selected = resolvedFoods.value
                            mealViewModel.addFoodsFromDiet(selected)
                            navController.navigate(Route.MealTemp.route)
                        },
                        value = "추가하기",
                        buttonHeight = 70f,
                        isOrange = true
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // "나만의 식단 생성하기" 버튼
                    Box(
                        modifier = Modifier
                            .padding(top = 28f.bhp())
                            .zIndex(1f)
                            .size(364f.wp(), 49f.bhp())
                            .clip(RoundedCornerShape(24f.bhp()))
                            .border(1.dp, Color.Black, RoundedCornerShape(24f.bhp()))
                            .noRippleClickable { navController.navigate(Route.DietCreate.route) },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_diet_cross),
                                contentDescription = "plus",
                                modifier = Modifier.size(14.2f.wp(), 14.2f.bhp())
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "나만의 식단 생성하기",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        }
                    }


                    Box(
                        modifier = Modifier
                            .padding(top = 20f.bhp())
                            .size(364f.wp(), 458f.bhp())
                            .clip(RoundedCornerShape(20f.wp()))
                            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.wp()))
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                                    radius = 1200f
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.img_diet_maintextballoon),
                            contentDescription = "text balloon",
                            modifier = Modifier
                                .size(219f.wp(), 83f.bhp())
                                .offset(x = 73f.wp(), y = 101f.bhp())
                        )
                        Box(
                            modifier = Modifier
                                .size(114f.wp(), 44f.bhp())
                                .offset(130.27f.wp(), 113f.bhp()),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "현재 식단이\n비어있어요!",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000),
                                textAlign = TextAlign.Center
                            )
                        }

                        // 마스코트/버튼
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            HamcoachGif(
                                modifier = Modifier.offset(y = 174f.bhp()),
                                num = 1,
                                ellipseLength = 182.0,
                                mascotLength = 160.0,
//                                onClick = {
//                                    navController.navigate(Route.DietExist.route)
//                                }
                            )
                        }

//                        EllipseNyam(
//                            modifier = Modifier
//                            .offset(x = 87f.wp(), y = 174f.bhp())
////                            .noRippleClickable(
////                                onClick = {
////                                    navController.navigate(Route.DietExist.route)
////                                }
////                            )
//                        ,
//                            ellipseLength = 182.0,
//                            mascotLength = 109.0
//                        )
                    }
                }
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
    MealPatchScreen(navController = navController)
}