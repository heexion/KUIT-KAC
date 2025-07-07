package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun MealItemCard(
    modifier: Modifier = Modifier,
    foodNum: Int,
    image: Int,
    foodName: String,
    foodAmount: Int,
    foodKcal: Int,
    onDeleteClick: () -> Unit
) {
    var amountText = "${foodAmount}개"
    // Todo: 추후에 음식 종류 선별해서 개/ml/접시 등등으로 바꿔야 함

    Row(
        modifier = modifier
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 11.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(68.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = foodName,
                style = DungGeunMo17,
                lineHeight = 22.sp,
                color = Color(0xFF713E3A),
            )
            Text(
                text = amountText, style = DungGeunMo15,
                lineHeight = 20.sp,
                color = Color(0xFF000000)
            )
            Text(
                text = "${foodKcal}kcal", style = DungGeunMo15,
                lineHeight = 20.sp,
                color = Color(0xFF000000)
            )
        }

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.padding(end = 3.dp).size(24.dp)
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
    MealItemCard(
        image = R.drawable.ic_beverages_p,
        foodName = "사이다",
        foodAmount = 1,
        foodKcal = 150,
        onDeleteClick = { },
        foodNum = 0
    )
}