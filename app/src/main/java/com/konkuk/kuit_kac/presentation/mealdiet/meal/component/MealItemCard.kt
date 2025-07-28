package com.konkuk.kuit_kac.presentation.mealdiet.meal.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun MealItemCard(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    foodNum: Int,
    image: Int,
    foodName: String,
    foodAmount: Float,
    foodKcal: Int,
    onDeleteClick: () -> Unit
) {
    var amountText = "${foodAmount}개"
    // Todo: 추후에 음식 종류 선별해서 개/ml/접시 등등으로 바꿔야 함
    Row(
        modifier = modifier
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.bhp()))
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF), RoundedCornerShape(16f.bhp()))
            .padding(horizontal = 11f.wp(), vertical = 8f.bhp())
            .clickable(
                onClick = {
                    navController.navigate(
                        "meal_search_detail/마라탕"
                    )
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(68f.wp(),68f.bhp())
        )

        Spacer(modifier = Modifier.width(12f.wp()))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6f.bhp())
        ) {
            Text(
                text = foodName,
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                lineHeight = 22f.isp(),
                color = Color(0xFF713E3A),
            )
            Text(
                text = amountText, style = DungGeunMo15,
                fontSize = 15f.isp(),
                lineHeight = 20f.isp(),
                color = Color(0xFF000000)
            )
            Text(
                text = "${foodKcal}kcal", style = DungGeunMo15,
                fontSize = 15f.isp(),
                lineHeight = 20f.isp(),
                color = Color(0xFF000000)
            )
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.padding(end = 3f.wp()).size(24f.wp(),24f.bhp())
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "삭제",
                tint = Color(0xFF999999)
            )
        }
    }
}

@Preview
@Composable
private fun MealItemCardPreview() {
    val navController = rememberNavController()
    MealItemCard(
        image = R.drawable.ic_chickenbreast,
        foodName = "고기만두",
        foodAmount = 1f,
        foodKcal = 120,
        onDeleteClick = { },
        foodNum = 0,
        navController = navController
    )
}