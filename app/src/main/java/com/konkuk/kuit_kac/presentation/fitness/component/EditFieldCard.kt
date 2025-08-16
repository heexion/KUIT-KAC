package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun EditFieldCard(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    unitLabel: String = "분"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24f.wp())
    ) {
        Row(
            modifier = Modifier
                .width(364f.wp())
                .height(59.9f.bhp())
                .padding(vertical = 8f.bhp())
                .clip(RoundedCornerShape(20f.wp()))
                .background(Color(0xFFFFFFFF))
                .padding(horizontal = 12f.wp()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = DungGeunMo17.copy(fontSize = 17f.isp()),
                color = Color(0xFF000000),
                modifier = Modifier.padding(start = 15.85f.wp())
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.14f.wp())
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = { input ->
                        val digitsOnly = input.filter { it.isDigit() }
                        onValueChange(if (digitsOnly.isEmpty()) "" else digitsOnly)
                    },
                    textStyle = DungGeunMo15.copy(
                        color = Color(0xFF000000),
                        textAlign = TextAlign.End,
                        fontSize = 15f.isp()
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .width(40f.wp())
                        .padding(end = 4f.wp())
                        .background(Color(0xFFFFFFFF))
                )


                Text(
                    text = unitLabel,
                    style = DungGeunMo17.copy(fontSize = 17f.isp()),
                    color = Color(0xFF000000)
                )
            }
        }
    }
}


@Preview
@Composable
private fun EditFieldCardPreview() {
    var anaerobicTimeText by remember { mutableStateOf("0") }

    EditFieldCard(
        title = "운동 시간",
        value = anaerobicTimeText,
        onValueChange = {
            anaerobicTimeText = it.filter { ch -> ch.isDigit() }
        },
        unitLabel = "분"
    )
}