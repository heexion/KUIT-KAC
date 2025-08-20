package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import com.konkuk.kuit_kac.ui.theme.deepYellow
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random


@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)))),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 280f.bhp())
                .height(220f.bhp())
        ) {
            Box(
                modifier = Modifier
                    .offset(126f.wp(), 5.3f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.img_diet_maintextballoon),
                    contentDescription = "text balloon",
                    modifier = Modifier
                        .size(232f.wp(), 90f.bhp())
                )
                Text(
                    modifier = Modifier.padding(bottom = 20f.bhp()),
                    text = "잠시만!\n계속 안되면 wifi 확인해줘",
                    textAlign = TextAlign.Center,
                    style = DungGeunMo17,
                    fontSize = 16.5f.isp(),
                    color = Color(0xFF000000)
                )
            }
            Box(
                modifier = Modifier
                    .size(150f.wp(), 150f.bhp())
                    .offset(x = 131f.wp(), y = 70f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                HamcoachGif(num = 1)
            }
        }
        Box(
            modifier = Modifier
                .size(100f.wp(), 100f.bhp())
                .padding(top = 120f.bhp())
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .matchParentSize()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}
