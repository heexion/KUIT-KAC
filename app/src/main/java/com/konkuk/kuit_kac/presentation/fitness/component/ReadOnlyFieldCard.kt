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
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
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
            .padding(horizontal = 24f.wp())
    ) {
        Row(
            modifier = Modifier
                .width(364f.wp())
                .height(59.9f.bhp())
                .padding(vertical = 8f.bhp())
                .clip(RoundedCornerShape(20f.wp()))
                .background(Color(0xFFFFFFFF))
                .padding(horizontal = 12f.wp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17.copy(fontSize = 17f.isp()),
                color = Color(0xFF000000),
                modifier = Modifier.padding(start = 15.85f.wp())
            )
            Text(
                text = "$value $unitLabel",
                style = DungGeunMo17.copy(fontSize = 17f.isp()),
                color = Color(0xFF000000),
                modifier = Modifier.padding(end = 16.14f.wp())
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