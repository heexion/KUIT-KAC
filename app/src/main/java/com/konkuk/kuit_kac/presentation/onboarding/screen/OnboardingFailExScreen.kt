package com.konkuk.kuit_kac.presentation.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.presentation.navigation.Route.OnboardingAppetite
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingFailExScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNextClick: (List<String>) -> Unit = {}
) {
    val options = listOf("야식", "스트레스성\n 폭식", "식단 기록\n 귀찮음", "의지 부족")
    val selectedOptions = remember { mutableStateListOf<String>() }

    var isDirectInputMode by remember { mutableStateOf(false) }
    var directInputText by remember { mutableStateOf("") }

    fun toggle(option: String) {
        if (selectedOptions.contains(option)) {
            selectedOptions.remove(option)
        } else {
            selectedOptions.add(option)
        }
    }


    val scrollState = rememberScrollState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0


    LaunchedEffect(isDirectInputMode, isKeyboardVisible) {
        if (isDirectInputMode && isKeyboardVisible) {
            delay(100)
            bringIntoViewRequester.bringIntoView()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {
        OnboardingBackScreen(
            bubbleText = "실패한 경험이 있다면,\n그 이유가 뭐라고 생각해?",
            bubbleFontSize = 24f.isp(),
            nyamAlpha = 0.5f
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = 24f.wp(),
                    end = 24f.wp(),
                    bottom = 50f.bhp() + WindowInsets.navigationBars
                        .asPaddingValues()
                        .calculateBottomPadding()
                )
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16f.wp())) {
                DietSelectButton(
                    value = options[0],
                    isSelected = selectedOptions.contains(options[0]),
                    buttonHeight = 70,
                    onClick = { toggle(options[0]) },
                    modifier = Modifier
                        .width(174f.wp())
                        .clip(RoundedCornerShape(20f.bhp()))
                )
                DietSelectButton(
                    value = options[1],
                    isSelected = selectedOptions.contains(options[1]),
                    buttonHeight = 70,
                    onClick = { toggle(options[1]) },
                    modifier = Modifier
                        .width(174f.wp())
                        .clip(RoundedCornerShape(20f.bhp()))
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16f.wp())) {
                DietSelectButton(
                    value = options[2],
                    isSelected = selectedOptions.contains(options[2]),
                    buttonHeight = 70,
                    onClick = { toggle(options[2]) },
                    modifier = Modifier
                        .width(174f.wp())
                        .clip(RoundedCornerShape(20f.bhp()))
                )
                DietSelectButton(
                    value = options[3],
                    isSelected = selectedOptions.contains(options[3]),
                    buttonHeight = 70,
                    onClick = { toggle(options[3]) },
                    modifier = Modifier
                        .width(174f.wp())
                        .clip(RoundedCornerShape(20f.bhp()))
                )
            }


            ConfirmButton(
                isDirectInputMode = isDirectInputMode,
                value = directInputText,
                onValueChange = { directInputText = it },
                isAvailable = selectedOptions.isNotEmpty() || directInputText.isNotBlank(),
                height = 65f,
                modifier = Modifier
                    .width(364f.wp())
                    .bringIntoViewRequester(bringIntoViewRequester),
                onClick = {
                    when {
                        // 직접 입력 모드이고 글자가 있으면 → 다음 화면 이동
                        isDirectInputMode && directInputText.isNotBlank() -> {
                            onNextClick(
                                (selectedOptions + directInputText).filter { it.isNotBlank() }
                            )
                            navController.navigate(OnboardingAppetite.route)
                        }
                        // 옵션 선택만 했어도 바로 이동
                        selectedOptions.isNotEmpty() -> {
                            onNextClick(selectedOptions)
                            navController.navigate(OnboardingAppetite.route)
                        }
                        // 직접 입력 모드 진입 (처음 누른 경우)
                        !isDirectInputMode -> {
                            isDirectInputMode = true
                            scope.launch {
                                delay(50)
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    }
                }
            )

        }
    }
}

@Composable
fun DietSelectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isSelected: Boolean = false,
    value: String,
    buttonHeight: Int,
) {
    val backgroundRes = if (isSelected) {
        R.drawable.img_dietfail_selected_button
    } else {
        R.drawable.img_dietfail_button
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight.toFloat().bhp())
            .clip(RoundedCornerShape(20f.bhp())) // 둥근 모서리 적용
            .noRippleClickable { onClick() }
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(backgroundRes),
            contentDescription = "select button",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        // 텍스트
        Text(
            text = value,
            style = DungGeunMo20,
            fontSize = 20f.isp(),
            lineHeight = 28f.isp(),
            color = Color(0xFF000000),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    isAvailable: Boolean = false,
    isDirectInputMode: Boolean = false,
    height: Float = 60f,
    onClick: () -> Unit = {}
) {
    var isEditingFinished by remember { mutableStateOf(false) }  // 입력 완료 상태
    val focusManager = LocalFocusManager.current

    val image = if (isAvailable || isEditingFinished) {
        R.drawable.bg_plan_confirm_button_selected
    } else {
        R.drawable.img_diet_confirm_button
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.bhp())
            .noRippleClickable {
                if (!isDirectInputMode || isEditingFinished || isAvailable) {
                    onClick()
                }
            }


    ) {
        // 버튼 배경
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(image),
            contentDescription = "confirm button",
            contentScale = ContentScale.FillBounds
        )

        when {
            // 직접 입력 모드 + 아직 입력 완료 안 됨 → 입력창
            isDirectInputMode && !isEditingFinished -> {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text(
                            text = "이유를 입력해주세요",
                            style = DungGeunMo20,
                            fontSize = 20f.isp(),
                            color = Color(0xFF888888),
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = DungGeunMo20.copy(
                        fontSize = 20f.isp(),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .onFocusChanged { focusState ->
                            if (!focusState.hasFocus && value.isNotBlank()) {
                                // 포커스 해제 + 값 있음 → 입력 완료 처리
                                isEditingFinished = true
                            }
                        }
                )
            }

            // 입력 완료 → 사용자가 입력한 문구 표시
            isEditingFinished -> {
                Text(
                    text = value,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            // 옵션 선택 → "다음 단계로"
            isAvailable -> {
                Text(
                    text = "다음 단계로",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // 아무 것도 안 했을 때 → "직접 입력할래"
            else -> {
                Text(
                    text = "직접 입력할래",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun OnboardingFailExScreenPreview() {
    OnboardingFailExScreen(
        navController = rememberNavController(),
        onNextClick = {},
        //onDirectInputClick = {}
    )
}
