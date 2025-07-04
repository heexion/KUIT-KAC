package com.konkuk.kuit_kac.presentation.home.homegraph.component

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
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun HomeGraphScreenBox(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Column(
        modifier = modifier
            .shadow(elevation = 0.dp, spotColor = Color(0x1A000000), ambientColor = Color(0x1A000000))
            .size(154.dp, 70.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color.Transparent)
            .border(1.dp, color = Color(0xFF000000), RoundedCornerShape(18.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = DungGeunMo15
        )
        Text(
            modifier = Modifier
                .padding(top = 2.dp),
            text = value,
            style = DungGeunMo20
        )
    }
}