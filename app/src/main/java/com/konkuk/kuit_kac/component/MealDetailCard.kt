package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.pretty
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24

@Composable
fun MealDetailCard(
    modifier: Modifier = Modifier,
    image: Int,
    foodName: String,
    carbohydrate: Float,  // g - 탄수
    protein: Float,       // g - 단백
    fat: Float,           // g - 지방
    baseCalories: Int,    // kcal - 기본 칼로리
    unitWeight: Int,      // g - 단위 무게
    isSpeechBubble: Boolean = true
) {
    var quantity by remember { mutableStateOf(0.5f) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Column(
                modifier = Modifier
                    .background(Color(0xFFFFF1AB), RoundedCornerShape(20f.bhp()))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                    .padding(horizontal = 15f.wp()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 11f.bhp())
                        .size(74f.wp(),74f.bhp()),
                    painter = painterResource(image),
                    contentDescription = "meal icon",
                )

                Text(
                    text = foodName,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .background(Color(0xFFFFFADF), RoundedCornerShape(8f.bhp()))
                        .padding(horizontal = 7f.wp(), vertical = 6f.bhp())
                )

                Spacer(modifier = Modifier.height(16f.bhp()))

                // 영양 정보
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFFFFF), RoundedCornerShape(15f.bhp()))
                        .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.bhp()))
                        .padding(vertical = 25f.bhp(), horizontal = 22f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "탄수화물",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                            Text(
                                "${carbohydrate.pretty()}g",
                                style = DungGeunMo20,
                                fontSize = 20f.isp(),
                                color = Color(0xFF713E3A),
                                modifier = Modifier.padding(top = 5f.bhp())
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "단백질",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                            Text(
                                "${protein.pretty()}g",
                                style = DungGeunMo20,
                                fontSize = 20f.isp(),
                                color = Color(0xFF713E3A),
                                modifier = Modifier.padding(top = 5f.bhp())
                            )
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "지방",
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF000000)
                            )
                            Text(
                                "${fat.pretty()}g",
                                style = DungGeunMo20,
                                fontSize = 20f.isp(),
                                color = Color(0xFF713E3A),
                                modifier = Modifier.padding(top = 5f.bhp())
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(18f.bhp()))
                    Spacer(
                        modifier = Modifier
                            .height(1f.bhp())
                            .fillMaxWidth()
                            .background(Color(0x3B000000))
                    )
                    Spacer(modifier = Modifier.height(20f.bhp()))

                    Text(
                        text = "${(baseCalories * quantity).toInt()} kcal",
                        style = DungGeunMo24,
                        fontSize = 24f.isp(),
                        color = Color(0xFF000000)
                    )
                }

                Spacer(modifier = Modifier.height(16f.bhp()))

                QuantitySelector(
                    quantity = quantity,
                    onQuantityChange = {
                        quantity = it.coerceIn(0.5f, 100.0f)
//                        quantity = it
                    }, //Todo: 상한선, 하한선 설정?
                    unitWeight = unitWeight
                )

                Spacer(modifier = Modifier.height(27f.bhp()))
            }
            if (isSpeechBubble)
                Spacer(modifier = Modifier.height(35f.bhp()))
        }
        if (isSpeechBubble)
            SpeechBubble(
                "여기서 말하는\n한 개는 음료캔 크기야!",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15f.wp())
            )
    }

}

// 수량 조절 영역
@Composable
fun QuantitySelector(
    quantity: Float,
    onQuantityChange: (Float) -> Unit,
    unitWeight: Int
) {

    var text by remember { mutableStateOf(quantity.toString()) }

    LaunchedEffect(quantity) {
        val formatted = if (quantity == quantity.toInt().toFloat()) {
            quantity.toInt().toString()
        } else {
            String.format("%.1f", quantity)
        }
        if (formatted != text) {
            text = formatted
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // "한 개" 버튼
        Box(
            modifier = Modifier
                .background(Color(0xFFFFD387), RoundedCornerShape(15f.bhp()))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.bhp()))
                .height(50f.bhp())
                .weight(0.5f)
        ) {
            Text(
                "한 개 (${unitWeight}g)", style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFF000000),
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(16f.wp()))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(50f.bhp())
                .background(Color(0xFFFFFFFF), RoundedCornerShape(15f.wp()))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(15f.wp()))
                .padding(horizontal = 19f.wp())
                .weight(0.5f)
        ) {
            Image(
                modifier = Modifier
                    .width(14.2f.wp())
                    .clickable {
                        onQuantityChange(quantity - 0.5f)
                    },
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = "minus button",
                contentScale = ContentScale.FillWidth
            )

//            Text(
//                text = quantity.pretty(),
//                style = DungGeunMo20,
//                color = Color(0xFF000000),
//                modifier = Modifier
//            )

            TextField(
                modifier = Modifier.width(70.dp),
                value = text,
                onValueChange = {
                    if (it.length <= 5 && it.matches(Regex("^\\d{0,3}(\\.\\d{0,1})?$"))) {
                        // Todo: 소수점 아래 한 자리까지 받는 정규식. 입력 형태에 따라 수정 가능
                        text = it
                        it.toFloatOrNull()?.let { newValue ->
                            onQuantityChange(newValue)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = DungGeunMo20.copy(
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
            )

            Image(
                modifier = Modifier
                    .width(14.2f.wp())
                    .clickable {
                        onQuantityChange(quantity + 0.5f)
                    },
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "minus button",
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

// 말풍선
@Composable
fun SpeechBubble(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.height(67f.bhp()),
            painter = painterResource(id = R.drawable.ic_meal_detail_speechbubble),
            contentDescription = "말풍선",
            contentScale = ContentScale.FillHeight
        )
        Text(
            text = text,
            style = DungGeunMo12,
            fontSize = 12f.isp(),
            lineHeight = 18f.isp(),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 6f.bhp()),
        )
    }
}


@Preview
@Composable
private fun MealDetailCardPreview() {
    MealDetailCard(
        modifier = Modifier,
        image = R.drawable.ic_fruits,
        foodName = "사과",
        carbohydrate = 30f,
        protein = 30f,
        fat = 0.1f,
        baseCalories = 130,
        unitWeight = 150,
        isSpeechBubble = true
    )
}

