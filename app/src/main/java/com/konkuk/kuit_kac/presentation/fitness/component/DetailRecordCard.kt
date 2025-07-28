package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun DetailRecordCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24f.wp())
    ) {
        Row(
            modifier = Modifier
                .width(364f.wp())
                .height(59.9f.bhp())
                .padding(vertical = 8f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(Color(0xFFFFFFFF))
                .padding(horizontal = 12f.wp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17,
                color = Color(0xFF000000),
                modifier = Modifier.padding(start = 15.85f.wp())
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.14f.wp())
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = DungGeunMo15.copy(
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Start
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .widthIn(min = 40f.wp(), max = 100f.wp())
                        .padding(end = 4f.wp())
                        .background(Color(0xFFFFFFFF))
                )

                Text(
                    text = "ex) 운동 횟수, 거리",
                    style = DungGeunMo15,
                    color = Color(0xFF999999),
                    fontSize = 13f.isp(),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailRecordCard() {
    var detail by remember { mutableStateOf("") }

    DetailRecordCard(
        title = "상세 기록",
        value = detail,
        onValueChange = { detail = it }
    )
}
