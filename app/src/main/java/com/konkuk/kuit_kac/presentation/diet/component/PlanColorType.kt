package com.konkuk.kuit_kac.presentation.diet.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun PlanColorType(
    value: String,
    image: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            modifier = Modifier.size(17f.wp(), 17f.bhp()),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
        )
        Text(
            text = value,
            style = DungGeunMo12,
            fontSize = 12f.isp(),
            lineHeight = 18f.isp(),
            color = Color(0xFF713E3A),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 5f.wp())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlanColorTypePreview() {
    PlanColorType(
        value = "식단",
        image = R.drawable.ic_plan_circle_blue
    )
}