package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun FitnessSetWeightCard(
    modifier: Modifier = Modifier,
    setNumber: Int = 1,
    weight: String = "",
    count: String = "",
    onWeightChange: (String) -> Unit = {},
    onCountChange: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .width(365f.wp())
            .height(60f.bhp())
            .clip(RoundedCornerShape(20f.bhp()))
            .background(Color(0xFFFFFFFF))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
            .padding(horizontal = 16f.wp()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 좌측: 세트 번호
        Text(
            text = "세트 $setNumber",
            style = DungGeunMo17,
            color = Color(0xFF000000)
        )

        // 우측: 무게 입력 + "kg" + 횟수 입력 + "회" + 삭제 버튼
        Row(verticalAlignment = Alignment.CenterVertically) {
            // 무게 입력 박스
            Box(
                modifier = Modifier
                    .width(62.6f.wp())
                    .height(31.6f.bhp())
                    .clip(RoundedCornerShape(10f.bhp()))
                    .background(Color(0xFFFFF1AB)),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = weight,
                    onValueChange = onWeightChange,
                    textStyle = DungGeunMo17.copy(
                        textAlign = TextAlign.Center,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(4f.wp()))

            Text(
                text = "kg",
                style = DungGeunMo17,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.width(8f.wp()))

            // 횟수 입력 박스
            Box(
                modifier = Modifier
                    .width(62.6f.wp())
                    .height(31.6f.bhp())
                    .clip(RoundedCornerShape(10f.bhp()))
                    .background(Color(0xFFFFF1AB)),
                contentAlignment = Alignment.Center
            ) {
                BasicTextField(
                    value = count,
                    onValueChange = onCountChange,
                    textStyle = DungGeunMo17.copy(
                        textAlign = TextAlign.Center,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(4f.wp()))

            Text(
                text = "회",
                style = DungGeunMo17,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.width(8f.wp()))

            // 삭제 버튼
            Box(
                modifier = Modifier
                    .size(24f.bhp())
                    .clickable { onDeleteClick() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "삭제",
                    modifier = Modifier.size(7.7f.bhp()),
                    colorFilter = ColorFilter.tint(Color(0xFF000000))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFitnessSetWeightCard() {
    var weight by remember { mutableStateOf("25") }
    var count by remember { mutableStateOf("10") }

    FitnessSetWeightCard(
        setNumber = 1,
        weight = weight,
        count = count,
        onWeightChange = { weight = it },
        onCountChange = { count = it },
        onDeleteClick = { /* 삭제 처리 */ }
    )
}
