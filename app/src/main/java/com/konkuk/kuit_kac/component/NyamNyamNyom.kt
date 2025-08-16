package com.konkuk.kuit_kac.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import com.konkuk.kuit_kac.ui.theme.deepYellow

@Composable
fun NyamNyamNyom(modifier: Modifier = Modifier) {
    var board by remember { mutableStateOf(Array(3) { IntArray(3) }) }
    var count by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    fun checkWinner(b: Array<IntArray>, p: Int): Boolean {
        for (r in 0..2) if ((0..2).all { c -> b[r][c] == p }) return true
        for (c in 0..2) if ((0..2).all { r -> b[r][c] == p }) return true
        if ((0..2).all { i -> b[i][i] == p }) return true
        if ((0..2).all { i -> b[i][2 - i] == p }) return true
        return false
    }

    @Composable
    fun eachBox(row: Int, column: Int, modifier: Modifier) {
        Box(
            modifier = modifier
                .aspectRatio(1f)
                .border(1.dp, Color(0xFF000000))
                .clickable {
                    if (board[row][column] == 0) {
                        val next = if (count % 2 == 0) 1 else 2
                        val newBoard = board.map { it.clone() }.toTypedArray()
                        newBoard[row][column] = next
                        board = newBoard
                        count += 1
                        if (checkWinner(newBoard, next)) showDialog = true
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            when (board[row][column]) {
                0 -> Box(Modifier)
                1 -> EllipseNyam(mascotLength = 80.0, ellipseLength = 200.0)
                2 -> Image(
                    modifier = Modifier.size(80f.wp(), 80f.bhp()),
                    painter = painterResource(R.drawable.img_main_person),
                    contentDescription = "you"
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFFF4C1))))
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 40f.bhp()),
            text = "냠냄뇸" + showDialog,
            style = DungGeunMo35,
            fontSize = 48f.isp()
        )

        Box(
            modifier = Modifier
                .padding(top = 60f.bhp())
                .size(350f.bhp())
        ) {
            Column(Modifier.fillMaxSize()) {
                for (r in 0..2) {
                    Row(Modifier.weight(1f)) {
                        for (c in 0..2) {
                            eachBox(r, c, Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(top = 60f.bhp(), start = 40f.bhp(), end = 40f.bhp())
                .fillMaxWidth()
                .height(80f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                .clickable {
                    board = Array(3) { IntArray(3) }
                    count = 0
                    showDialog = false
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "다시하기", style = DungGeunMo24, fontSize = 24f.isp())
        }
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 50f.bhp())
        )
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = {showDialog=false}
        ) {
            Box(
                modifier = Modifier
                    .size(160f.bhp())
                    .background(Color(0xFFFFFFFF))
            ){
                Text(text = "이겼어", style = DungGeunMo24,
                    fontSize = 24f.isp())
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NyamNyamNyomPreview(){
    NyamNyamNyom()
}