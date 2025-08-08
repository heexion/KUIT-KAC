package com.konkuk.kuit_kac.presentation.fitness.component

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

data class FitnessItemData(
    val imageRes: Int,
    val name: String,
    val setCount: Int,
    val kcal: Int,
    val onDeleteClick: () -> Unit
)

@Composable
fun FitnessRecordCard(
    title: String,
    fitnessItems: List<Fitness>,
    onEditClick: () -> Unit,
    navController: NavHostController,
    isEditable: Boolean = false
) {
    Column(
        modifier = Modifier
            .width(364f.wp())
            .wrapContentHeight()
            .clip(RoundedCornerShape(16f.bhp()))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(16f.bhp()))
            .background(Color(0xFFFFFFFF))
    ) {
        // 상단 헤더
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
                Text(
                    text = title,
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(30.16f.wp(), 29.66f.bhp())
                        .clickable {
                            onEditClick()
                        }
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
                            .size(26.76f.wp(), 26.76f.bhp())
                            .align(Alignment.Center)
                    )
                }
            }
        }

        // 운동 항목 리스트 (배경 포함)
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_fitnessrecordcard),
                contentDescription = "운동 리스트 배경",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(
                        bottomStart = 16f.bhp(),
                        bottomEnd = 16f.bhp()
                    ))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16f.wp()), // 이미지와 텍스트 간 여백
                verticalArrangement = Arrangement.spacedBy(16f.bhp())
            ) {
                fitnessItems.forEachIndexed { index, item ->
                    FitnessItemCard(
                        FitnessNum = index,
                        image = R.drawable.ic_lowerbody,
                        FitnessName = item.name,
                        FitnessAmount = 2,
                        FitnessKcal = 110,
                        onDeleteClick = {},
                        isEditable = isEditable
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FitnessRecordCardPreview() {
    val navController = rememberNavController()

    // 샘플 데이터
    val sampleFitnessItems = listOf(
        FitnessItemData(
            imageRes = R.drawable.ic_lowerbody,
            name = "레그 컬",
            setCount = 2,
            kcal = 120,
            onDeleteClick = {}
        ),
        FitnessItemData(
            imageRes = R.drawable.ic_lowerbody,
            name = "레그 프레스",
            setCount = 2,
            kcal = 120,
            onDeleteClick = {}
        ),
        FitnessItemData(
            imageRes = R.drawable.ic_lowerbody,
            name = "레그 익스텐션",
            setCount = 2,
            kcal = 120,
            onDeleteClick = {}
        )
    )

}
