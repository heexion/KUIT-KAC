package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp

@Composable
fun FitnessSetAddCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(365f.wp())
            .height(60f.bhp())
            .clip(RoundedCornerShape(20f.bhp()))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp())) // 테두리만 있음
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "세트 추가",
                modifier = Modifier
                    .size(14.2f.bhp()),
                colorFilter = ColorFilter.tint(Color(0xFF000000))
            )
            Spacer(modifier = Modifier.width(10.4f.wp()))
            Text(
                text = "세트 추가",
                style = DungGeunMo20,
                color = Color(0xFF000000)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFitnessSetAddCard() {
    FitnessSetAddCard(onClick = { /* 세트 추가 동작 */ })
}
