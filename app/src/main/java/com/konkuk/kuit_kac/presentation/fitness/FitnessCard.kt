package com.konkuk.kuit_kac.presentation.fitness

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
    isEditable: Boolean = false,
    routineList: List<String> = listOf("루틴1", "루틴2", "루틴3"),
    onRoutineSelect: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTitle by remember { mutableStateOf(title) }
    var isEditingTitle by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(top = 28f.bhp(), start = 24f.wp())
            .width(364f.wp())
            .clip(RoundedCornerShape(20f.wp()))
            .background(Color(0xFFFFF1AB))
            .border(1.dp, Color.Black, RoundedCornerShape(20f.wp()))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 22f.bhp(), start = 24f.wp())
                    .width(176f.wp())
                    .height(28f.bhp())
                    .clip(RoundedCornerShape(7f.wp()))
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
                if (isEditingTitle && isEditable) {
                    TextField(
                        value = selectedTitle,
                        onValueChange = {
                            selectedTitle = it
                            onRoutineSelect(it) // 실시간 반영
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .height(28f.bhp()),
                        textStyle = DungGeunMo17.copy(
                            fontSize = 17f.isp(),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )

                        ,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { isEditingTitle = false }
                        )
                    )
                } else {
                    Text(
                        text = selectedTitle,
                        style = DungGeunMo17,
                        fontSize = 17f.isp(),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }

                // 드롭다운 아이콘 (보낸 아이콘), 편집화면일 때는 숨김
                if (!isEditable) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dropdown), // 두 번째 이미지 참고
                        contentDescription = "드롭다운",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp)
                            .size(12.dp)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(223f.wp())
                        .height(114f.bhp())
                        .clip(RoundedCornerShape(6f.wp()))
                        .background(Color(0xFFFFF6C3))
                ) {
                    routineList.forEachIndexed { index, routine ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = routine,
                                    style = DungGeunMo17,
                                    fontSize = 16f.isp(),
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            onClick = {
                                selectedTitle = routine
                                onRoutineSelect(routine)
                                expanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38f.bhp()) // 전체 높이 114 중 3등분
                        )

                        // 루틴 구분선 (마지막 항목 뒤에는 생략)
                        if (index < routineList.lastIndex) {
                            Divider(
                                color = Color(0xFF999999),
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(horizontal = 7f.wp()) // (223 - 208.686) ≈ 14.3, 좌우 약 7씩
                                    .width(208.686f.wp())
                                    .height(1.dp)
                            )
                        }
                    }
                }

            }

            // 펜 아이콘은 편집 모드일 때만 표시
            if (isEditable) {
                Box(
                    modifier = Modifier
                        .padding(top = 22f.bhp(), start = 48.79f.wp())
                        .size(26.75811f.wp(), 26.75811f.bhp())
                        .clip(RoundedCornerShape(13.27905f.wp()))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.White, Color(0xFFFFB638))
                            )
                        )
                        .clickable {
                            navController.navigate("fitness_edit")
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
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(84.dp)
            .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp))
            .padding(horizontal = 11f.wp(), vertical = 8f.bhp()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(68f.bhp())
        )

        Spacer(modifier = Modifier.width(12f.wp()))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6f.bhp())
        ) {
            Text(
                text = FitnessName,
                style = DungGeunMo17,
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
