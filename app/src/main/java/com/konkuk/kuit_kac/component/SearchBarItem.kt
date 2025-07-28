package com.konkuk.kuit_kac.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun SearchBarItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    value: String,
    isLastItem: Boolean = false,
    isCloseIconExist: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(horizontal = 16f.wp(), vertical = 16f.bhp()),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = value,
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF000000),
            )

            if (isCloseIconExist)
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "삭제 아이콘",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .size(24f.wp(),24f.bhp())
                )
        }

        // 마지막 아이템인 경우 아래의 회색 가로선을 표시하지 않음
        /*if (!isLastItem) {
            Spacer(
                modifier = Modifier
                    .height(1f.bhp())
                    .fillMaxWidth()
                    .background(Color(0x3B000000))
            )
        }*/
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBarItemPreview() {
    SearchBarItem(
        value = "마라탕"
    )
}