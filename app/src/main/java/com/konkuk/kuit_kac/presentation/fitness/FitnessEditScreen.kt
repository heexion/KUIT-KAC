package com.konkuk.kuit_kac.presentation.fitness

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.EllipseNyam
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo15
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessEditScreen(modifier: Modifier = Modifier,
                      navController: NavHostController,
                      fitnessList: MutableList<FitnessData>
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .background(
                brush =
                    Brush.radialGradient(
                        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1)),
                        radius = 2000f
                    )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20f.wp(), top = 20f.bhp()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arow),
                contentDescription = "Back",
                modifier = Modifier
                    .size(24f.wp())
                    .clickable { navController.popBackStack() }
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 12.51f.hp())
                .fillMaxWidth()
        ){
            Image(
                modifier = Modifier
                    .offset(x = 78f.wp())
                    .size(272f.wp(),96f.hp()),
                painter = painterResource(R.drawable.img_diet_patchballoon),
                contentDescription = "textballoon"
            )
            EllipseNyam(
                modifier = Modifier
                    .offset(y = 72f.hp(), x = 117f.wp()),
                ellipseLength = 177.17578, mascotLength = 106.1115
            )
            Column(
                modifier = Modifier
                    .padding(top = 256f.hp(), start = 24f.wp())
                    .width(364f.wp())
                    .clip(RoundedCornerShape(20f.wp()))
                    .background(color = Color(0xFFFFF1AB))
                    .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.wp())),
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22f.bhp(), start = 94f.wp(), end = 94f.wp())
                        .height(28f.bhp())
                        .clip(RoundedCornerShape(7f.wp()))
                        .background(color = Color(0xFFFFFCEE)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        modifier = Modifier,
                        text = "하체루틴_허벅지..",
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(top = 20f.bhp(), start = 16f.wp(), end = 15f.wp()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16f.bhp())
                ) {
                    fitnessList.forEach { item ->
                        FitnessItem(
                            FitnessNum = item.id,
                            image = item.imageRes,
                            FitnessName = item.name,
                            isEditable = true,
                            onDeleteClick = {
                                fitnessList.remove(item)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8f.bhp()))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 11f.wp(), vertical = 8f.bhp())
                        .height(84f.bhp())
                        .clip(RoundedCornerShape(15f.wp()))
                        .background(color = Color(0xFFFFFFFF))
                        .border(1.dp,Color(0xFF000000), RoundedCornerShape(15f.wp()))
                        .clickable {
                            navController.navigate("fitness_search")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(19f.wp(),19f.bhp())
                            .padding(end = 7f.wp()),
                        painter = painterResource(R.drawable.img_diet_plus),
                        contentDescription = "plus"
                    )
                    Text(
                        text = "운동 추가하기",
                        style = DungGeunMo15,
                        fontSize = 15f.isp(),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(19f.bhp())
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32f.bhp(), start = 17f.wp(), end = 15f.wp())
            .height(70f.bhp())
            .clip(RoundedCornerShape(20f.wp()))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFFFFF),Color(0xFFFFB638))
                )
            )
            .border(2.dp, Color(0xFF000000),RoundedCornerShape(20f.wp()))
            .clickable { navController.navigate("fitness_edit_result") },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "저장하기",
            style = DungGeunMo20,
            textAlign = TextAlign.Center,
            fontSize = 20f.isp(),
            color = Color(0xFF000000)
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115f.hp())
            .background(Color.Transparent)
    )
}


@Preview(showBackground = true)
@Composable
fun FitnessEditScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sampleList =remember { mutableStateListOf(
        FitnessData(1, "레그 컬", R.drawable.ic_lowerbody, {}),
        FitnessData(2, "레그 프레스", R.drawable.ic_lowerbody, {}),
        FitnessData(3, "레그 익스텐션", R.drawable.ic_lowerbody, {})
    )}

    FitnessEditScreen(
        navController = navController,
        fitnessList = sampleList
    )
}