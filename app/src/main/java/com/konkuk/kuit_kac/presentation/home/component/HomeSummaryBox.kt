package com.konkuk.kuit_kac.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeSummaryBox(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    width : Dp,
    height: Dp
) {
    Column(
        modifier = modifier
            .shadow(elevation = 0.dp, spotColor = Color(0x1A000000), ambientColor = Color(0x1A000000))
            .size(154f.wp(), 70f.bhp())
            .clip(RoundedCornerShape(0.117 * width))
            .background(color = Color.Transparent)
            .border(1.dp, color = Color(0xFF000000), RoundedCornerShape(18f.wp())),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = DungGeunMo15,
            fontSize = 15f.isp(),
            color = Color(0xFF000000)
        )
        Text(
            modifier = Modifier
                .padding(top = 2f.bhp()),
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF000000)
        )
    }
}