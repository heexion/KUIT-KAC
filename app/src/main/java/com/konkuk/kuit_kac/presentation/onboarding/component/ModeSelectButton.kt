package com.konkuk.kuit_kac.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun ModeSelectButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconRes: Int,
    iconSize: Dp,
    title: String,
    description: String,
    showBadge: Boolean = false,
    isSelected: Boolean = false
) {
    val backgroundRes = if (isSelected) {
        R.drawable.bg_yellow_button_selected
    } else {
        R.drawable.bg_yellow_button_default
    }

    Box(
        modifier = modifier
            .width(364f.wp())
            .height(85f.bhp())
            .clickable { onClick() }
    ) {
        // 배경 이미지
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // 컨텐츠
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30f.wp()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 아이콘
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize) // 전달받은 iconSize 사용
                    .padding(end = 12f.wp())
            )

            // 텍스트 (제목 + 설명)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
                Spacer(modifier = Modifier.height(2f.bhp()))
                Text(
                    text = description,
                    style = DungGeunMo20,
                    fontSize = 15f.isp(),
                    color = Color(0xFF713E3A)
                )
            }

            // 추천 뱃지
            if (showBadge) {
                Box(
                    modifier = Modifier
                        .offset(y = (-10f.bhp()))
                        .background(
                            color = Color(0xFFFFBA47),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8f.wp(), vertical = 4f.bhp())
                ) {
                    Text(
                        text = "추천!",
                        style = DungGeunMo20,
                        fontSize = 12f.isp(),
                        color = Color(0xFF000000)
                    )
                }
            }
        }
    }
}



@Preview
@Composable
private fun ModeSelectPreview() {
    ModeSelectButton(
        iconRes = R.drawable.ic_mode_nyam,
        iconSize = 51.dp,
        title = "냠냠모드",
        description = "가볍고 꾸준하게",
        showBadge = false,
        onClick = { /* TODO */ }
    )

    ModeSelectButton(
        iconRes = R.drawable.ic_mode_coach,
        iconSize = 64.dp,
        title = "코치모드",
        description = "가볍고 꾸준하게",
        showBadge = true,
        onClick = { /* TODO */ }
    )

    ModeSelectButton(

        iconRes = R.drawable.ic_mode_allin,
        iconSize = 50.dp,
        title = "올인모드",
        description = "가볍고 꾸준하게",
        showBadge = false,
        onClick = { /* TODO */ }
    )

}