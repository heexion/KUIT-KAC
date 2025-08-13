package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingInputResult
import com.konkuk.kuit_kac.presentation.onboarding.component.SubButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun OnboardingInputScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedMode: String, // 추가: 선택한 모드 받기
    onNextClick: (List<String>) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    val bubbleText = "이제 기본적인\n정보 입력이 필요해!"

    var selectedGender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weightCurrent by remember { mutableStateOf("") }
    var weightTarget by remember { mutableStateOf("") }

    val isAvailable = selectedGender.isNotEmpty()
            && age.isNotEmpty()
            && height.isNotEmpty()
            && weightCurrent.isNotEmpty()
            && weightTarget.isNotEmpty()

    // 모달 표시 여부
    var showDialog by remember { mutableStateOf(false) }

    // 다음 화면 이동 함수
    fun proceedNext() {
        onNextClick(
            listOf(selectedGender, age, height, weightCurrent, weightTarget)
        )
        navController.navigate(OnboardingInputResult.route)
    }

    // 모달 UI
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .width(364f.wp())
                    .height(202f.bhp())
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0xFFFEF9EC))
                    .border(2.dp, Color(0xFF000000), RoundedCornerShape(30.dp))
                    .padding(vertical = 32f.bhp(), horizontal = 24f.wp())
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "닫기",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(20.dp)
                        .clickable { showDialog = false }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "기간 대비 감량폭이 커!\n계속 진행할까?",
                        style = DungGeunMo20,
                        fontSize = 20f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24f.bhp()))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12f.wp()),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12f.wp()),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // 수정하기 버튼
                            PlanConfirmButtonCustom(
                                backgroundRes = R.drawable.bg_onboarding_input_edit_button,
                                onClick = { showDialog = false }
                            )

                            // 진행하기 버튼
                            PlanConfirmButtonCustom(
                                backgroundRes = R.drawable.bg_onboarding_input_go_button,
                                onClick = {
                                    showDialog = false
                                    proceedNext()
                                }
                            )
                        }


                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        // 기존 UI 그대로 유지
        Box(modifier = Modifier.fillMaxWidth()) {
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
        }

        Spacer(modifier = Modifier.height(18.9f.hp()))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.4f.wp()),
            verticalAlignment = Alignment.Top
        ) {
            HamcoachGif(
                num = 1,
                ellipseLength = 191.2,
                mascotLength = 165.0,
            )

            Box(
                modifier = Modifier
                    .size(width = 228.6f.wp(), height = 110.2f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_onboarding_bubble),
                    contentDescription = "말풍선",
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = bubbleText,
                    style = DungGeunMo17.copy(fontSize = 17f.isp()),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12f.wp())
                        .offset(y = (-12f).bhp())
                )
            }
        }

        Spacer(modifier = Modifier.height(24.7f.bhp()))

        // 성별
        Box(
            modifier = Modifier
                .padding(horizontal = 24.4f.wp())
                .fillMaxWidth()
                .height(84f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboarding_input),
                contentDescription = "성별 선택 배경",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20f.wp(), end = 20f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "1. 성별",
                    style = DungGeunMo20,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12f.wp()),
                    modifier = Modifier.padding(start = 60f.wp())
                ) {
                    SubButton(
                        value = "남자",
                        buttonHeight = 60,
                        buttonWidth = 100,
                        isSelected = selectedGender == "남자",
                        onClick = { selectedGender = "남자" }
                    )
                    SubButton(
                        value = "여자",
                        buttonHeight = 60,
                        buttonWidth = 100,
                        isSelected = selectedGender == "여자",
                        onClick = { selectedGender = "여자" }
                    )
                }
            }
        }

        // 나이
        Box(
            modifier = Modifier
                .padding(start = 24.4f.wp(), end = 24.4f.wp(), top = 16f.bhp())
                .fillMaxWidth()
                .height(84f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboarding_input),
                contentDescription = "나이 입력 배경",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20f.wp(), end = 20f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "2. 나이",
                    style = DungGeunMo20,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )

                AgeInputBox(
                    age = age,
                    onAgeChange = { age = it }
                )
            }
        }

        // 키
        Box(
            modifier = Modifier
                .padding(start = 24.4f.wp(), end = 24.4f.wp(), top = 16f.bhp())
                .fillMaxWidth()
                .height(84f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboarding_input),
                contentDescription = "키 입력 배경",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 20f.wp(), end = 20f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "3. 키",
                    style = DungGeunMo20,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )

                HeightInputBox(
                    height = height,
                    onHeightChange = { height = it }
                )
            }
        }

        // 체중
        Box(
            modifier = Modifier
                .padding(start = 24.4f.wp(), end = 24.4f.wp(), top = 16f.bhp())
                .fillMaxWidth()
                .height(122f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_onboarding_input_big),
                contentDescription = "체중 입력 배경",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16f.wp()),
                verticalArrangement = Arrangement.spacedBy(12f.bhp())
            ) {
                Text(
                    text = "4. 체중",
                    style = DungGeunMo20,
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12f.wp()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeightInputBox(
                        label = "현재",
                        value = weightCurrent,
                        onValueChange = { weightCurrent = it })
                    WeightInputBox(
                        label = "목표",
                        value = weightTarget,
                        onValueChange = { weightTarget = it })
                }
            }
        }

        Spacer(modifier = Modifier.height(33f.bhp()))

        // 다음 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32f.bhp()),
            contentAlignment = Alignment.Center
        ) {
            PlanConfirmButton(
                isAvailable = isAvailable,
                value = "다음 단계로",
                height = 65f,
                modifier = Modifier.width(364f.wp()),
                onClick = {
                    if (!isAvailable) return@PlanConfirmButton

                    val lossAmount =
                        (weightCurrent.toFloatOrNull() ?: 0f) -
                                (weightTarget.toFloatOrNull() ?: 0f)

                    val limit = when (selectedMode) {
                        "냠냠모드" -> 12
                        "코치모드" -> 8
                        "올인모드" -> 5
                        else -> Int.MAX_VALUE
                    }

                    if (lossAmount >= limit) {
                        showDialog = true
                    } else {
                        proceedNext()
                    }
                }
            )
        }
    }
}



@Composable
fun AgeInputBox(
    age: String,
    onAgeChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 112f.wp())
            .width(160f.wp())
            .height(59f.bhp())
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_input_button),
            contentDescription = "나이 입력 배경",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12f.wp())
        ) {
            // 입력창
            BasicTextField(
                value = age,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        onAgeChange(newValue)
                    }
                },
                textStyle = DungGeunMo20.copy(
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                ),
                singleLine = true,
                modifier = Modifier.width(60f.wp()),
                decorationBox = { innerTextField ->
                    if (age.isEmpty()) {
                        Text(
                            text = "______",
                            style = DungGeunMo20.copy(
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        )
                    }
                    innerTextField() // 실제 입력 필드
                }
            )

            Spacer(modifier = Modifier.width(4f.wp()))
            Text(
                text = "세",
                style = DungGeunMo20.copy(fontSize = 17f.isp()),
                color = Color(0xFF000000)
            )
        }
    }
}

@Composable
fun HeightInputBox(
    height: String,
    onHeightChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 112f.wp())
            .width(160f.wp())
            .height(59f.bhp())
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_input_button),
            contentDescription = "키 입력 배경",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12f.wp())
        ) {
            BasicTextField(
                value = height,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        onHeightChange(newValue)
                    }
                },
                textStyle = DungGeunMo20.copy(
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                ),
                singleLine = true,
                modifier = Modifier.width(60f.wp()),
                decorationBox = { innerTextField ->
                    if (height.isEmpty()) {
                        Text(
                            text = "______",
                            style = DungGeunMo17.copy(
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(4f.wp()))
            Text(
                text = "cm",
                style = DungGeunMo17.copy(fontSize = 17f.isp()),
                color = Color(0xFF000000)
            )
        }
    }
}

@Composable
fun WeightInputBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .width(160f.wp())
            .height(60f.bhp())
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_input_button),
            contentDescription = "${label} 체중 입력 배경",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12f.wp()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$label:",
                style = DungGeunMo20,
                fontSize = 17f.isp(),
                color = Color(0xFF000000),
                modifier = Modifier.width(60f.wp())
            )

            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        onValueChange(newValue)
                    }
                },
                textStyle = DungGeunMo20.copy(
                    fontSize = 17f.isp(),
                    color = Color(0xFF000000)
                ),
                singleLine = true,
                modifier = Modifier.width(50f.wp()),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = "_____",
                            style = DungGeunMo20.copy(
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                        )
                    }
                    innerTextField()
                }
            )

            Text(
                text = "kg",
                style = DungGeunMo20,
                fontSize = 17f.isp(),
                color = Color(0xFF000000)
            )
        }
    }
}

@Composable
fun PlanConfirmButtonCustom(
    modifier: Modifier = Modifier,
    backgroundRes: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(126f.wp())
            .height(56f.bhp())
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(backgroundRes),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}




@Preview(showBackground = true)
@Composable
private fun OnboardingInputScreenPreview() {
    val navController = rememberNavController()
    OnboardingInputScreen(
        navController = navController,
        selectedMode = "코치모드", // 테스트용 모드
        onNextClick = {}
    )
}
