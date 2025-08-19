package com.konkuk.kuit_kac.presentation.login.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.BuildConfig
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun LoginMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 119f.hp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login_nyamcoach_title),
                contentDescription = "냠코치 타이틀",
                modifier = Modifier
                    .size(width = 186f.wp(), height = 62f.bhp())
                    .padding(bottom = 16f.bhp())
            )
            Text(
                modifier = Modifier,
                text = "픽셀 친구들과 다이어트도 즐겁게!",
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFF713E3A),
            )
        }

        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 600.86f.hp(), start = 199f.wp(), end = 65f.wp())
                .size(height = 50f.bhp(), width = 180f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(Color(0xB2E1D9B7)),
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .alpha(0.5f)
                .padding(top = 610.99f.hp(), start = 50.69f.wp(), end = 263.79f.wp())
                .size(height = 33f.bhp(), width = 103f.wp()),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.ic_hamcoach_backlight),
            colorFilter = ColorFilter.tint(Color(0xB2E1D9B7)),
            contentDescription = null,
        )
        EllipseNyam(
            mascotLength = 110.0,
            ellipseLength = 185.0,
            modifier = Modifier
                .offset(x = 10f.wp(), y = 272f.hp())
        )
        Image(
            modifier = Modifier
                .padding(top = 333.12f.hp(), start = 165.5f.wp())
                .size(223f.wp(), 300f.bhp()),
            painter = painterResource(id = R.drawable.img_nyamee_normal),
            contentDescription = null,
        )

        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(), end = 24f.wp(),
                    bottom = 85f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                )
                .size(346.6669f.wp(), 52f.bhp())
                .clickable {
                    navController.navigate(Route.LoginEmail.route)
//                    val kakaoLoginUrl = "${BuildConfig.BASE_URL}/api/auth/kakao"
//                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(kakaoLoginUrl))
//                    context.startActivity(intent)
                },
            painter = painterResource(id = R.drawable.ic_kakao_login_wide),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginMainScreenPreview() {
    val navController = rememberNavController()
    LoginMainScreen(navController = navController)
}