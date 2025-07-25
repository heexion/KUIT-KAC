package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun ReadOnlyFieldCard(
    title: String,
    value: String,
    unitLabel: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .width(364.dp)
                .height(59.9.dp)
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFFFFFF))
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17,
                color = Color(0xFF000000),
                modifier = Modifier.padding(start = 15.85.dp)
            )
            Text(
                text = "$value $unitLabel",
                style = DungGeunMo17,
                color = Color(0xFF000000),
                modifier = Modifier.padding(end = 16.14.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ReadOnlyFieldCardPreview() {
    ReadOnlyFieldCard(
        title = "예상 소모 칼로리",
        value = "300".toString(),
        unitLabel = "kcal"
    )
}