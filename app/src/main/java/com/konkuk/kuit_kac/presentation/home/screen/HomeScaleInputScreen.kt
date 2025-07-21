package com.konkuk.kuit_kac.presentation.home.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HomeBackgroundComponent
import com.konkuk.kuit_kac.presentation.home.component.HomeSubBackgroundComponent
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35

@Composable
fun HomeScaleInputScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    val animatedPaddingTop by animateDpAsState(
        targetValue = if (isKeyboardVisible) 10.dp else 81.02.dp,
        label = "ScaleImagePadding"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.TopCenter
    ) {
        HomeBackgroundComponent()
        HomeSubBackgroundComponent(
            modifier = Modifier
                .offset(y = 477.73.dp)
        )
        Image(
            modifier = Modifier
                .padding(top = animatedPaddingTop)
                .size(350.dp),
            painter = painterResource(id = R.drawable.ic_scale_home),
            contentDescription = "homescale scale bg",
        )
    }

    WeightInputModal(
        modifier = Modifier,
        initialWeight = "",
        onConfirm = { weight ->
            println("입력된 체중: $weight")
        },
        navController = navController,
        isKeyboardVisible = isKeyboardVisible
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScaleInputScreenPreview() {
    val navController = rememberNavController()
    HomeScaleInputScreen(
        navController = navController,
        modifier = Modifier
    )
}
// push

@Composable
fun WeightInputModal(
    modifier: Modifier,
    initialWeight: String = "",
    onConfirm: (String) -> Unit,
    navController: NavHostController,
    isKeyboardVisible: Boolean
) {
    var weight by remember { mutableStateOf(initialWeight) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
            .background(
                if (isKeyboardVisible) Color(0x60000000)
                else Color(0x80000000)
            )
            .clickable {
                focusManager.clearFocus()
            },
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_weight),
                contentDescription = null,
                modifier = Modifier
                    .width(300f.wp()),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "<현재 체중>",
                style = DungGeunMo24,
                fontSize = 24f.isp(),
                color = Color(0xFF000000),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 55f.bhp())
            )
            Text(
                text = "45kg",
                style = DungGeunMo35,
                fontSize = 35f.isp(),
                color = Color(0xFF713E3A),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 25f.bhp())
            )
        }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 60f.wp(), topEnd = 60f.wp()))
                .height(333.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                    )
                )
                .border(1.25.dp, Color.Black, RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .padding(horizontal = 25f.wp(), vertical = 25f.bhp())
                .clickable(enabled = false) {},
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "체중을 입력해줘!",
                style = DungGeunMo24,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 7.89.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 48.88f.bhp()),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .padding(horizontal = 20f.wp(), vertical = 12f.bhp())
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFFF3B7))
                ) {
                    TextField(
                        value = weight,
                        onValueChange = {
                            if (it.length <= 5 && it.matches(Regex("^\\d{0,3}(\\.\\d{0,1})?$"))) {
                                weight = it
                            }
                        },
                        textStyle = TextStyle(
                            fontFamily = DungGeunMo,
                            fontSize = 40.sp,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .focusRequester(focusRequester),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                navController.navigate(Route.HomeResult.route)
                            }
                        )
                    )
                }
                Text(
                    text = "kg",
                    style = DungGeunMo35,
                    color = Color(0xFF000000),
                )
            }

            Spacer(modifier = Modifier.height(20f.bhp()))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    ,
                contentAlignment = Alignment.Center
            ) {
            }
        }
    }
}

@Composable
fun Int.toDp(): Dp {
    return with(LocalDensity.current) { this@toDp.toDp() }
}