package com.konkuk.kuit_kac.presentation.diet.screen

import android.util.Log
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.MealViewModel
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanDietCard
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

// 날짜별 식단 추가 화면
@Composable
fun PlanIPAddScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mealViewModel: MealViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        mealViewModel.getPlan(userId = 1)
    }
    val meal = mealViewModel.getPlanState.value
    fun isMealTypeExist(type: String): Boolean {
        return meal?.any { it.dietType == type }?:false
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 20f.hp())
                    .align(Alignment.TopCenter)
            )

//            EllipseNyam(
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 112.12f.hp()),
//                mascotLength = 87.70016,
//                ellipseLength = 145.62891
//            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HamcoachGif(
                    modifier = Modifier.offset(y = 112.12f.hp()),
                    num = 1,
                    ellipseLength = 145.62891,
                    mascotLength = 110.0,
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(top = 59f.hp()), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                    modifier = Modifier.size(248.0013f.wp(), 82.99957f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = "식단 계획이\n어떻게 돼?",
                    style = DungGeunMo17,
                    fontSize = 17f.isp(),
                    lineHeight = 22f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24f.bhp())
                )
            }


            Column(
                modifier = Modifier
                    .padding(top = 247.59f.hp(), start = 24f.wp(), end = 24f.wp()),
            ) {
                Row(
                    Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFF000000),
                            shape = RoundedCornerShape(size = 42f.bhp())
                        )
                        .width(363f.wp())
                        .height(37f.bhp())
                        .background(
                            color = Color(0xFFFFF1AB), shape = RoundedCornerShape(size = 42f.bhp())
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = mealViewModel.planDate.value?:"2025-12-12",
                        style = DungGeunMo17,
                        fontSize = 17f.isp()
                    )
                    //Todo: 달력 구현 후 추가
                }
                Spacer(modifier = Modifier.size(22.5f.bhp()))
                PlanDietCard(
                    "아침",
                    listOf(if(isMealTypeExist("아침")){ "아침 식단"}
                    else{"식단 추가" }
                    ),
                    onClick = {
                        mealViewModel.setType("아침")
                        navController.navigate("PlanIPSearch") },
                    editOnClick = {mealViewModel.resolveFoodsFromPlanType("아침") {
                        Log.d("PlanIPAddName", mealViewModel.selectedFoods.first().food.name)
                        navController.navigate("PlanIPTemp")
                    }},
                    isEdit = isMealTypeExist("아침")
                )
                Spacer(modifier = Modifier.size(20f.bhp()))
                PlanDietCard(
                    "점심",
                    listOf(if(isMealTypeExist("점심")){ "점심 식단"}
                    else{"식단 추가" }
                    ),
                    onClick = {
                        mealViewModel.setType("점심")
                        navController.navigate("PlanIPSearch") },
                    editOnClick = {
                        if (mealViewModel.getPlanState.value != null) {
                            mealViewModel.resolveFoodsFromPlanType("점심") {
                                Log.d("PlanIPAddName", mealViewModel.selectedFoods.first().food.name)
                                navController.navigate("PlanIPTemp")
                            }
                        } else {
                            Log.e("PlanIPAdd", "Plan data not ready yet!")
                        }
                    },
                    isEdit = isMealTypeExist("점심")
                )
                Spacer(modifier = Modifier.size(20f.bhp()))
                PlanDietCard(
                    "저녁",
                    listOf(if(isMealTypeExist("저녁")){ "식단 추가"}
                        else{"저녁식단" }
                    ),
                    onClick = {
                        mealViewModel.setType("저녁")
                        navController.navigate("PlanIPSearch") },
                    editOnClick = {
                        if (mealViewModel.getPlanState.value != null) {
                            mealViewModel.resolveFoodsFromPlanType("저녁") {
                                Log.d("PlanIPAddName", mealViewModel.selectedFoods.first().food.name)
                                navController.navigate("PlanIPTemp")
                            }
                        } else {
                            Log.e("PlanIPAdd", "Plan data not ready yet!")
                        }
                    },
                    isEdit = isMealTypeExist("저녁")
                )
                Spacer(modifier = Modifier.size(40.46f.bhp()))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(65f.bhp())
                        .clickable {
                            if(isMealTypeExist("아침")&&isMealTypeExist("점심")&&isMealTypeExist("저녁")){
                                navController.navigate(Route.MealFastingResult.route)
                            }
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .height(65f.bhp()),
                        painter = painterResource(R.drawable.bg_button_gray),
                        contentDescription = "default button",
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = "저장하기",
                        style = DungGeunMo20,
                        fontSize = 22f.isp(),
                        lineHeight = 28.sp,
                        color = Color(0xFF000000),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = Modifier.size(120.2f.dp))
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanIPAddScreenPreview() {
    val navController = rememberNavController()
    PlanIPAddScreen(
        navController = navController
    )
}