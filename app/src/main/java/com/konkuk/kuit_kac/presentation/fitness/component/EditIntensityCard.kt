package com.konkuk.kuit_kac.presentation.fitness.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun EditIntensityCard(
    title: String = "운동 강도",
    selectedIndex: Int,
    onSelect: (Int) -> Unit
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
                horizontalArrangement = Arrangement.spacedBy(12f.wp()),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.14f.wp())
            ) {
                listOf(
                    R.drawable.ic_easy to R.drawable.ic_easy_fill,
                    R.drawable.ic_normal to R.drawable.ic_normal_fill,
                    R.drawable.ic_hard to R.drawable.ic_hard_fill
                ).forEachIndexed { index, (normalIcon, selectedIcon) ->
                    val iconRes = if (selectedIndex == index) selectedIcon else normalIcon
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "운동 강도 $index",
                        modifier = Modifier
                            .size(28f.wp())
                            .noRippleClickable { onSelect(index) }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun EditIntensityCardPreview() {
    var selectedIntensity by remember { mutableStateOf(-1) }
    EditIntensityCard(
        selectedIndex = selectedIntensity,
        onSelect = { selectedIntensity = it }
    )

}