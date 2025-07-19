package com.konkuk.kuit_kac.presentation.fitness.component

import android.util.Log
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17


data class FitnessData(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val onDeleteClick: () -> Unit
)

@Composable
fun FitnessCard(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    title: String,
    fitnessList: List<FitnessData>,
    isEditable: Boolean = true,
    routineList: List<String> = listOf("루틴1", "루틴2", "루틴3"),
    onRoutineSelect: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTitle by remember { mutableStateOf(title) }
    var isEditingTitle by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20f.bhp()))
            .background(Color(0xFFFFF1AB))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 22f.bhp(), start = 50f.wp())
                    .width(223f.wp())
                    .height(28f.bhp())
                    .clip(RoundedCornerShape(7f.bhp()))
                    .background(Color(0xFFFFFCEE))
                    .clickable {
                        if (isEditable) {
                            isEditingTitle = true
                        } else {
                            expanded = true
                        }
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    if (isEditingTitle && isEditable) {
                        TextField(
                            value = selectedTitle,
                            onValueChange = {
                                selectedTitle = it
                                onRoutineSelect(it)
                            },
                            modifier = Modifier
                                .width(223f.wp())
                                .heightIn(max = 56f.bhp()),
                            singleLine = true,
                            textStyle = DungGeunMo17.copy(
                                fontSize = 17f.isp(),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { isEditingTitle = false })
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .width(223f.wp())
                                .height(28f.bhp())
                                .clip(RoundedCornerShape(7f.bhp()))
                                .background(Color(0xFFFFFCEE))
                                .clickable { expanded = true },
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = selectedTitle,
                                textAlign = TextAlign.Center,
                                style = DungGeunMo17,
                                fontSize = 17f.isp(),
                                color = Color(0xFF0000000),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8f.wp())
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.ic_dropdown),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 7f.wp())
                                    .size(12f.wp(), 12f.bhp())
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(223f.wp())
                                .height(114f.bhp())
                                .background(Color(0xFFFFF6C3))
                        ) {
                            routineList.forEachIndexed { index, routine ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = routine,
                                            fontSize = 16f.isp(),
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    },
                                    onClick = {
                                        selectedTitle = routine
                                        onRoutineSelect(routine)
                                        expanded = false
                                    }
                                )
                                if (index < routineList.lastIndex) {
                                    Divider(
                                        color = Color(0xFF999999),
                                        modifier = Modifier.padding(horizontal = 7f.wp())
                                    )
                                }
                            }
                        }
                    }
                }


            }

            // 펜 아이콘은 편집 모드일 때만 표시
            if (isEditable) {
                Box(
                    modifier = Modifier
                        .padding(top = 22f.bhp(), start = 13.9f.wp())
                        .size(26.75811f.wp(), 26.75811f.bhp())
                        .clip(RoundedCornerShape(13.27905f.bhp()))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFB638))
                            )
                        )
                        .clickable {
                            navController.navigate(Route.FitnessEdit.route)
                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_diet_pen),
                        contentDescription = "pen",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
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
                    onDeleteClick = item.onDeleteClick,
                    isEditable = isEditable
                )
            }
            Spacer(modifier = Modifier.width(16f.wp()))
        }
    }
}



@Composable
fun FitnessItem(
    modifier: Modifier = Modifier,
    FitnessNum: Int,
    image: Int,
    FitnessName: String,
    onDeleteClick: () -> Unit,
    isEditable: Boolean = false
) {
    Row(
        modifier = modifier
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(16f.bhp()))
            .fillMaxWidth()
            .height(84f.bhp())
            .background(Color(0xFFFFFFFF), RoundedCornerShape(16f.bhp()))
            .padding(horizontal = 11f.wp(), vertical = 8f.bhp()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(68f.wp(),68f.bhp())
        )

        Spacer(modifier = Modifier.width(12f.wp()))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6f.bhp())
        ) {
            Text(
                text = FitnessName,
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                lineHeight = 22f.isp(),
                color = Color(0xFF713E3A),
            )
        }



        if (isEditable) {
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier
                    .padding(end = 3f.wp())
                    .size(24f.bhp())
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "삭제",
                    tint = Color(0xFF999999)
                )
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun FitnessCardPreview() {
    val navController = rememberNavController()

    // 샘플 리스트 (삭제 이벤트는 Log로 처리)
    val sampleList = remember {
        mutableStateListOf(
            FitnessData(
                id = 1,
                name = "레그 컬",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 컬 삭제됨") }
            ),
            FitnessData(
                id = 2,
                name = "레그 프레스",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 프레스 삭제됨") }
            ),
            FitnessData(
                id = 3,
                name = "레그 익스텐션",
                imageRes = R.drawable.ic_lowerbody,
                onDeleteClick = { Log.d("Fitness", "레그 익스텐션 삭제됨") }
            )
        )
    }

    // 선택된 루틴 Log 출력
    FitnessCard(
        navController = navController,
        title = "하체루틴_허벅지..",
        fitnessList = sampleList,
        isEditable = true, // true로 바꾸면 펜 아이콘 보이고 드롭다운 사라짐
        routineList = listOf("하체루틴", "전신루틴", "상체루틴"),
        onRoutineSelect = { selected ->
            Log.d("선택된 루틴", selected)
        }
    )
}
