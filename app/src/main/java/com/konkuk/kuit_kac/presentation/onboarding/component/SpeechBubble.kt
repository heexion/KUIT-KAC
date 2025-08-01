package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun SpeechBubble(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16f.wp()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_onbubble),
            contentDescription = "말풍선 배경",
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = text,
            style = DungGeunMo20.copy(fontSize = fontSize),
            color = Color(0xFF000000),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24f.wp(), vertical = 16f.bhp()),
            textAlign = TextAlign.Center
        )
    }
}
