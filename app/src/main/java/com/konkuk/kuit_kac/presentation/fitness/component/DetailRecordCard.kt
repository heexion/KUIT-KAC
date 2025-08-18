package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun DetailRecordCard(
    modifier: Modifier = Modifier,
    title: String,
    lines: List<String>,
    placeholder: String = "ex) 운동 횟수, 거리",
    onClick:() -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24f.wp())
    ) {
        Row(
            modifier = Modifier
                .width(364f.wp())
                .clip(RoundedCornerShape(20f.bhp()))
                .background(Color(0xFFFFFFFF))
                .clickable { onClick() }
                .padding(horizontal = 12f.wp(), vertical = 8f.bhp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17,
                color = Color(0xFF000000),
                modifier = Modifier
                    .padding(start = 15.85f.wp())
                    .weight(1f)
            )

            if (lines.isEmpty()) {
                Text(
                    text = placeholder,
                    style = DungGeunMo15,
                    color = Color(0xFF999999),
                    fontSize = 13f.isp(),
                    maxLines = 1
                )
            } else {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4f.bhp()),
                    modifier = Modifier.widthIn(min = 40f.wp(), max = 180f.wp())
                ) {
                    lines.forEach { line ->
                        Text(text = line, style = DungGeunMo15, color = Color(0xFF000000))
                    }
                }
            }
        }
    }
}

