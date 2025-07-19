
package com.konkuk.kuit_kac.presentation.mealdiet.meal.component


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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo12
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun MealCard(
    mealType: String,
    totalKcal: String,
    foodList: List<Triple<Painter, String, String>>, // (icon, name, quantity)
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(364f.wp())
            .wrapContentHeight()
            .clip(RoundedCornerShape(16f.bhp()))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(16f.bhp()))
            .background(Color(0xFFFFFFFF))
    ) {
        // 헤더
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.855f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_diet_card),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16f.wp(), vertical = 11.1f.bhp()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "$mealType ", style = DungGeunMo20,
                    fontSize = 20f.isp(), color = Color(0xFF000000))
                Text(text = "(총 $totalKcal)", style = DungGeunMo17,
                    fontSize = 17f.isp(), color = Color(0xFF713E3A))
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(30.16f.wp(),29.66f.bhp())
                        .clickable { onEditClick() }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_button_pencil),
                        contentDescription = "Edit Button",
                        modifier = Modifier.matchParentSize()
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_record),
                        contentDescription = "Pencil Icon",
                        modifier = Modifier
                            .size(26.76f.wp(),26.76f.bhp())
                            .align(Alignment.Center)
                    )
                }
            }
        }

        // 배경 + 음식 리스트
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(154f.bhp())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_diet_card_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(bottomStart = 16f.bhp(), bottomEnd = 16f.bhp()))
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 17f.wp()),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                foodList.forEach { (icon, name, quantity) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = icon,
                            contentDescription = name,
                            modifier = Modifier.size(72f.wp(),72f.bhp())
                        )
                        Spacer(modifier = Modifier.height(6f.bhp()))
                        Text(
                            text = name,
                            style = DungGeunMo15,
                            fontSize = 15f.isp(),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(2f.bhp()))
                        Text(
                            text = quantity,
                            style = DungGeunMo12,
                            fontSize = 12f.isp(),
                            color = Color(0xFF713E3A),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MealCardPreview() {
    MealCard(
        mealType = "아침",
        totalKcal = "390kcal",
        foodList = listOf(
            Triple(painterResource(R.drawable.ic_sweetpotato), "고구마", "0.5개"),
            Triple(painterResource(R.drawable.ic_misc_foods), "단백질\n쉐이크", "300ml"),
            Triple(painterResource(R.drawable.ic_chickenbreast), "닭가슴살", "0.5접시"),
            Triple(painterResource(R.drawable.ic_salad), "단호박\n샐러드", "0.5개")
        ),
        onEditClick = { /* 편집 */ }
    )
}
