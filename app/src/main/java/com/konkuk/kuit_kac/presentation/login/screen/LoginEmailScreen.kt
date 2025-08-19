package com.konkuk.kuit_kac.presentation.login.screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.DefaultButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.data.request.Agreement
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.presentation.onboarding.OnboardingViewModel
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun LoginEmailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    onboardingViewModel.setNickname("닉네임")
    //TODO: nickname 여기서 받으면 여기서 수정해주세요
    val email: String = "user_id@naver.com"
    var showSheet by remember { mutableStateOf(true) }
    var agreeAll by remember { mutableStateOf(false) }
    var agreeService by remember { mutableStateOf(false) }
    var agreePrivacy by remember { mutableStateOf(false) }
    var agreeOptional by remember { mutableStateOf(false) }
    var sheetHeight = 452f.bhp()
    val heightSecond = 797f.bhp()

    var showPolicy by remember { mutableStateOf(false) }

    var policyText by remember { mutableStateOf("이용약관") }
    val policyService =
        "서비스 이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관\n이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관\n이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관\n이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관\n이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관이용약관\n이\n용\n약\n관"
    val policyPrivacy = "개인정보 수집 및 이용 동의 이용약관"
    val policyOptional = "선택 약관"

    val offsetY by animateDpAsState(
        targetValue = if (showSheet) 0.dp else sheetHeight,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
        label = "sheetOffset"
    )
    val offsetY2 by animateDpAsState(
        targetValue = if (showSheet) 0.dp else heightSecond,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
        label = "sheetOffset"
    )

    LaunchedEffect(agreeService, agreePrivacy, agreeOptional) {
        agreeAll = agreeService && agreePrivacy && agreeOptional
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 79f.hp()), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_speech_bubble_white_right),
                modifier = Modifier.size(365.5f.wp(), 129f.bhp()),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                text = "이메일을 확인해줘!",
                style = DungGeunMo24,
                fontSize = 24f.isp(),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24f.bhp())
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 24f.wp(), end = 24f.wp(), top = 240f.hp())
                .border(
                    width = 1.dp,
                    color = Color(0xFF000000),
                    shape = RoundedCornerShape(size = 18.dp)
                )
                .fillMaxWidth()
                .height(70f.bhp())
                .background(color = Color.Transparent, shape = RoundedCornerShape(size = 18.dp))
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16f.wp())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_login_kakao),
                    modifier = Modifier.size(38f.wp(), 38f.bhp()),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )
                Text(
                    text = email,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .padding(start = 16f.wp())
                        .align(Alignment.CenterVertically)
                )
            }
        }

        DefaultButton(
            onClick = { showSheet = true },
            value = "다음으로",
            buttonHeight = 70f,
            isOrange = true,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(), end = 24f.wp(),
                    bottom = 80f.bhp() + WindowInsets.navigationBars.asPaddingValues()
                        .calculateBottomPadding()
                )
        )

        if (showSheet) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x90000000))
                    .noRippleClickable {
                        if (showPolicy) {
                            showPolicy = false
                        } else
                            showSheet = false
                    } // 바깥 클릭 시 닫힘
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = offsetY),
                verticalArrangement = Arrangement.Bottom
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                        .height(452f.bhp())
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                            )
                        )
                        .border(
                            1.25.dp,
                            Color.Black,
                            RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp())
                        )
                        .clickable(enabled = false) {},
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 25f.wp(), vertical = 25f.bhp()),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "약관에 동의해줘",
                            fontSize = 24f.isp(),
                            style = DungGeunMo24,
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(top = 7.89f.bhp(), bottom = 25f.bhp())
                        )
                        Row(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF000000),
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                .fillMaxWidth()
                                .height(59f.bhp())
                                .background(
                                    color = Color(0xFFFFF1AB),
                                    shape = RoundedCornerShape(size = 10.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.size(16f.wp()))

                            CheckBoxLogin(
                                chekced = agreeAll,
                                onCheckedChanged = {
                                    val newValue = !agreeAll
                                    agreeAll = newValue
                                    agreeService = newValue
                                    agreePrivacy = newValue
                                    agreeOptional = newValue
                                })
                            Text(
                                text = "약관 전체 동의",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000),
                                modifier = Modifier
                                    .padding(start = 16f.wp())
                            )
                        }
                        Spacer(modifier = Modifier.size(29f.bhp()))
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(27f.bhp())
                                    .padding(start = 16f.wp()),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CheckBoxLogin(
                                    chekced = agreeService,
                                    onCheckedChanged = { agreeService = it }
                                )
                                Text(
                                    text = "서비스 이용약관 전체동의(필수)",
                                    style = DungGeunMo15,
                                    fontSize = 15f.isp(),
                                    color = Color(0xB2292929),
                                    modifier = Modifier
                                        .padding(start = 15f.wp())
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.ic_login_right_arrow),
                                modifier = Modifier
                                    .size(7f.wp(), 14f.bhp())
                                    .align(Alignment.CenterEnd)
                                    .noRippleClickable {
                                        policyText = policyService
                                        showPolicy = true
                                    },
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.size(19f.bhp()))
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(27f.bhp())
                                    .padding(start = 16f.wp()),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CheckBoxLogin(
                                    chekced = agreePrivacy,
                                    onCheckedChanged = { agreePrivacy = it }
                                )
                                Text(
                                    text = "개인 정보 수집 및 이용 동의(필수)",
                                    style = DungGeunMo15,
                                    fontSize = 15f.isp(),
                                    color = Color(0xB2292929),
                                    modifier = Modifier
                                        .padding(start = 15f.wp())
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.ic_login_right_arrow),
                                modifier = Modifier
                                    .size(7f.wp(), 14f.bhp())
                                    .align(Alignment.CenterEnd)
                                    .noRippleClickable {
                                        policyText = policyPrivacy
                                        showPolicy = true
                                    },
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.size(19f.bhp()))
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(27f.bhp())
                                    .padding(start = 16f.wp()),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CheckBoxLogin(
                                    chekced = agreeOptional,
                                    onCheckedChanged = { agreeOptional = it }
                                )
                                Text(
                                    text = "선택약관(선택)",
                                    style = DungGeunMo15,
                                    fontSize = 15f.isp(),
                                    color = Color(0xB2292929),
                                    modifier = Modifier
                                        .padding(start = 15f.wp())
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.ic_login_right_arrow),
                                modifier = Modifier
                                    .size(7f.wp(), 14f.bhp())
                                    .align(Alignment.CenterEnd)
                                    .noRippleClickable {
                                        policyText = policyOptional
                                        showPolicy = true
                                    },
                                contentScale = ContentScale.FillBounds,
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.size(28f.bhp()))
                        DefaultButton(
                            onClick = {navController.navigate(route = Route.OnboardingStart.route)},
                            value = "확인했어",
                            buttonHeight = 70f,
                            isOrange = true,
                            isUnableToClick = !agreeAll
                        )
                    }
                }
            }
            if (showPolicy)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp()))
                        .height(797f.bhp())
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                            )
                        )
                        .border(
                            1.25.dp,
                            Color.Black,
                            RoundedCornerShape(topStart = 75f.wp(), topEnd = 75f.wp())
                        )
                        .align(Alignment.BottomCenter)
                ) {

                    Text(
                        text = "이용약관",
                        style = DungGeunMo24,
                        fontSize = 24f.isp(),
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .padding(top = 43f.bhp())
                            .align(Alignment.TopCenter)
                    )
                    // 스크롤 가능한 약관
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 105f.bhp(), bottom = 140f.bhp()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = policyText,
                                style = DungGeunMo15,
                                fontSize = 15f.isp(),
                                lineHeight = 20f.isp(),
                                color = Color(0xFF000000),
                                modifier = Modifier.padding(
                                    bottom = 30f.wp(),
                                    start = 40f.bhp(),
                                    end = 40f.bhp()
                                )
                            )
                        }
                    }

                    // 하단 고정 버튼
                    DefaultButton(
                        onClick = { showPolicy = false },
                        value = "확인했어",
                        buttonHeight = 70f,
                        isOrange = true,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 24f.wp(), vertical = 25f.bhp()),
                    )
                }

        }

    }
}

@Preview
@Composable
private fun LoginEmailScreenPreview() {
    val navController = rememberNavController()
    LoginEmailScreen(navController = navController)
}

@Composable
fun CheckBoxLogin(
    modifier: Modifier = Modifier,
    chekced: Boolean = false,
    onCheckedChanged: (Boolean) -> Unit = {}
) {
    var checkbox: Int = R.drawable.ic_login_unchecked_box
    if (chekced) checkbox = R.drawable.ic_login_checked_box
    Image(
        painter = painterResource(id = checkbox),
        modifier = Modifier
            .size(27f.wp(), 27f.bhp())
            .noRippleClickable {
                onCheckedChanged(!chekced)
            },
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
    )
}