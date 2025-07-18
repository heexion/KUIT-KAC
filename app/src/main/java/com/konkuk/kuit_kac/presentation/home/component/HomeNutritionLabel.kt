package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun HomeNutritionLabel(
    modifier: Modifier = Modifier,
    content: String,
    color: Long
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 0.dp, spotColor = Color(0xFF000000), ambientColor = Color(0xFFFFFFFF))
                .size(19.42188f.wp())
                .clip(RoundedCornerShape(9.71094f.wp()))
                .background(color = Color(color))
                .border(1.dp, color = Color.Black, shape = RoundedCornerShape(9.71094f.wp()))
        )
        Text(
            modifier = Modifier
                .padding(start = 7f.wp()),
            text = content,
            style = DungGeunMo17,
            fontSize = 17f.isp()
        )
    }
}