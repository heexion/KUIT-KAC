package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.home.component.NyameeGif
import com.konkuk.kuit_kac.presentation.onboarding.component.NyamCoach
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import kotlin.random.Random

@Composable
fun OnboardingBackScreen(
    modifier: Modifier = Modifier,
    bubbleText: String = "반가워!\n냠코치를 찾아와줘서 고마워!\n간단하게 몇 가지만 물어볼게",
    bubbleFontSize: TextUnit = 18f.isp(),
    nyamAlpha: Float = 1f
) {
    val shadow = Color(0xFFDDD7BC)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3C1),
                        Color(0xFFFFFCEE),
                        Color(0xFFFFF3C1)
                    )
                )
            )
    ) {
        // 상단 타이틀
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            color = Color(0xFF713E3A),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20f.hp())
                .align(Alignment.TopCenter)
        )

        // 말풍선
        Box(
            modifier = Modifier
                .padding(top = 79f.hp(), start = 24f.wp())
                .size(width = 364f.wp(), height = 206f.bhp())
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_onbubble),
                contentDescription = "말풍선",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = bubbleText,
                style = DungGeunMo20.copy(fontSize = bubbleFontSize),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24f.wp())
                    .offset(y = (-16f).bhp())
            )
        }

        // 햄코치 조명 (오른쪽)
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 638.86f.hp(), start = 180f.wp())
                .size(width = 181f.wp(), height = 50f.bhp())
                .align(Alignment.TopStart),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )

        // 햄코치 조명 (왼쪽)
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 648f.hp(), start = 39.68f.wp())
                .size(width = 67.6f.wp(), height = 33f.hp())
                .align(Alignment.TopStart),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(shadow),
            contentDescription = null,
        )

//        // Ellipse + 햄코치 (왼쪽)
//        EllipseNyam(
//            mascotLength = 139.0,
//            ellipseLength = 232.0,
//            modifier = Modifier.offset(x = -20f.wp(), y = 255f.hp())
//        )
//
//        // 냠코치 (오른쪽)
//        NyamCoach(
//            modifier = Modifier
//                .padding(top = 342.12f.hp(), start = 126f.wp())
//                .size(width = 338f.wp(), height = 338f.bhp()),
//            alpha = nyamAlpha
//        )

        HamcoachGif(
            modifier = Modifier.offset(x = (-30f).wp(), y = 255f.hp()),
            num = 1,
            ellipseLength = 222.0,
            mascotLength = 200.0
        )

        NyameeGif(
            modifier = Modifier
                .offset(x = 60f.wp(), y = 300f.bhp())
                .alpha(nyamAlpha),
            num = 1,
            sizePercent = 1.35f
        )

    }
}


@Preview
@Composable
private fun OnboardingBackScreenPreview() {


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        OnboardingBackScreen(
            bubbleText = "", // 실제 사용 안함, 아래 커스텀으로 대체
        )

        // 커스텀 말풍선만 따로 오버레이
        Box(
            modifier = Modifier
                .padding(top = 79f.hp(), start = 24f.wp())
                .size(width = 364f.wp(), height = 206f.bhp())
                .align(Alignment.TopStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_onbubble),
                contentDescription = "말풍선",
                modifier = Modifier.fillMaxSize()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .offset(y = (-16).dp)
            ) {
                Text(
                    text = "반가워!",
                    style = DungGeunMo20.copy(fontSize = 35.sp, color = Color.Black),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(11.6.dp))
                Text(
                    text = "냠코치를 찾아와줘서 고마워!\n간단하게 몇 가지만 물어볼게",
                    style = DungGeunMo20.copy(fontSize = 17.sp, color = Color.Black),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}
