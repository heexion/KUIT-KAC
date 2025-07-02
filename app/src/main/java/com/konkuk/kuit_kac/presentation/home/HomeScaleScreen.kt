package com.konkuk.kuit_kac.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.ui.theme.DungGeunMo
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35

@Composable
fun HomeScaleScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            painter = painterResource(id = R.drawable.bg_home),
            contentDescription = "homescale home bg",
            contentScale = ContentScale.Crop
        )
        Text(
            text = "냠코치",
            style = DungGeunMo20,
            color = Color(0xFF713E3A),
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp)
        )
        Image(
            modifier = Modifier
                .padding(top = 10.dp)
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
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScaleScreenPreview() {
    HomeScaleScreen()
}


@Composable
fun WeightInputModal(
    modifier: Modifier,
    initialWeight: String = "",
    onConfirm: (String) -> Unit,
) {
    var weight by remember { mutableStateOf(initialWeight) }
    val focusManager = LocalFocusManager.current
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (isKeyboardVisible) Color(0x80000000)
                else Color.Transparent
            )
            .imePadding()
            .clickable {
                focusManager.clearFocus()
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .imePadding()
                .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEDD0))
                    )
                )
                .border(1.25.dp, Color.Black, RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                .padding(25.dp)
                .clickable(enabled = false) {},
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "체중을 입력해줘!",
                style = DungGeunMo24,
                modifier = Modifier.padding(bottom = 60.dp, top = 7.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 12.dp)
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
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .width(150.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            errorContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Text(
                    text = "kg",
                    style = DungGeunMo35,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    onConfirm(weight)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xFFFFFFFF), Color(0xFFFFE667))
                        )
                    )
                    .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            ) {
                Text(
                    text = "기록하기",
                    style = DungGeunMo20,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 14.dp)
                )
            }
        }
    }
}