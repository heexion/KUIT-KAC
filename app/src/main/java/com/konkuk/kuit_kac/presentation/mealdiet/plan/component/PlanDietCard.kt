package com.konkuk.kuit_kac.presentation.diet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15

@Composable
fun PlanDietCard(
    dietTime: String,
    dietValue: List<String>,
    onClick: () -> Unit = {},
    isEdit: Boolean = true
) {
    Column(
        Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(10f.bhp()))
    ) {
        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF000000),
                    shape = RoundedCornerShape(
                        topStart = 10f.bhp(),
                        topEnd = 10f.bhp(),
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFE667),
                    shape = RoundedCornerShape(
                        topStart = 10f.bhp(),
                        topEnd = 10f.bhp(),
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        ) {
            Text(
                text = dietTime,
                style = DungGeunMo15,
                fontSize = 15f.isp(),
                color = Color(0xFF000000),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16.06f.wp(), vertical = 7.71f.bhp())
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFF1AB),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 10f.bhp(),
                        bottomEnd = 10f.bhp()
                    )
                )
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                if (!isEdit)
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        modifier = Modifier
                            .padding(start = 16f.wp())
                            .size(11f.wp(), 11f.bhp())
                            .align(Alignment.CenterVertically)
                            .clickable { onClick() },
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null,
                    )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.06f.wp(), vertical = 12.84f.bhp())
                ) {
                    dietValue.forEach { item ->
                        Text(
                            text = item,
                            style = DungGeunMo12,
                            fontSize = 12f.isp(),
                            lineHeight = 18f.isp(),
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(vertical = 2f.bhp())
                        )
                    }
                }
            }

            if (isEdit)
                Image(
                    painter = painterResource(id = R.drawable.ic_record),
                    modifier = Modifier
                        .padding(end = 11.06f.wp())
                        .size(25.35508f.wp(), 25.35508f.bhp())
                        .align(Alignment.CenterEnd)
                        .clickable { onClick() },
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanDietCardPreview() {
    PlanDietCard(
        dietTime = "아침 / 편집/추가 선택 가능",
        dietValue = listOf("닭가슴살 300g", "단호박 샐러드 1접시", "고구마 반개"),
        isEdit = true
    )
}