package com.konkuk.kuit_kac.presentation.fitness.component

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
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun FitnessItemCard(
    modifier: Modifier = Modifier,
    FitnessNum: Int,
    image: Int,
    FitnessName: String,
    FitnessAmount: Int,
    FitnessKcal: Int,
    onDeleteClick: () -> Unit,
    isEditable: Boolean = false
) {
    Row(
        modifier = modifier
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.bhp()))
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF), RoundedCornerShape(16f.bhp()))
            .padding(horizontal = 11f.wp(), vertical = 8f.bhp()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(68f.bhp())
        )

        Spacer(modifier = Modifier.width(12f.wp()))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6f.bhp())
        ) {
            Text(
                text = FitnessName,
                style = DungGeunMo17,
                lineHeight = 22f.isp(),
                color = Color(0xFF713E3A),
            )
            Text(
                text = "세트 수: ${FitnessAmount}회",
                style = DungGeunMo15,
                lineHeight = 20f.isp(),
                color = Color(0xFF000000)
            )
            Text(
                text = "소모 칼로리: ${FitnessKcal}kcal",
                style = DungGeunMo15,
                lineHeight = 20f.isp(),
                color = Color(0xFF000000)
            )
        }

        if (isEditable) {
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .padding(end = 3f.wp())
                    .size(24f.bhp())
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "삭제",
                    tint = Color(0xFF999999)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MealItemCardPreview() {
    FitnessItemCard(
        image = R.drawable.ic_lowerbody,
        FitnessName = "레그 컬",
        FitnessAmount = 2,
        FitnessKcal = 120,
        onDeleteClick = { },
        FitnessNum = 0,
      // isEditable = true
    )
}