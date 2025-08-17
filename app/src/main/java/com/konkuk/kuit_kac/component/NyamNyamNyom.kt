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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.presentation.home.component.HamcoachGif
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17
import com.konkuk.kuit_kac.ui.theme.DungGeunMo24
import com.konkuk.kuit_kac.ui.theme.DungGeunMo35
import com.konkuk.kuit_kac.ui.theme.deepYellow
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

private enum class Difficulty { EASY, MEDIUM, HARD }

@Composable
fun NyamNyamNyom(modifier: Modifier = Modifier) {
    var board by remember { mutableStateOf(Array(3) { IntArray(3) }) }
    var currentPlayer by remember { mutableStateOf(2) }
    var moveCount by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("이겼어") }
    var aiThinking by remember { mutableStateOf(false) }
    var difficulty by remember { mutableStateOf(Difficulty.EASY) }

    val focusManager = LocalFocusManager.current

    fun cloneBoard(b: Array<IntArray>) = b.map { it.clone() }.toTypedArray()

    fun winner(b: Array<IntArray>): Int? {
        fun line(a: Int, b0: Int, c: Int) = if (a == b0 && b0 == c && a != 0) a else 0
        for (r in 0..2) {
            val w = line(b[r][0], b[r][1], b[r][2]); if (w != 0) return w
        }
        for (c in 0..2) {
            val w = line(b[0][c], b[1][c], b[2][c]); if (w != 0) return w
        }
        val w1 = line(b[0][0], b[1][1], b[2][2]); if (w1 != 0) return w1
        val w2 = line(b[0][2], b[1][1], b[2][0]); if (w2 != 0) return w2
        return null
    }

    fun isTerminal(b: Array<IntArray>): Boolean =
        winner(b) != null || b.all { row -> row.all { it != 0 } }

    fun emptyCells(b: Array<IntArray>): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        for (r in 0..2) for (c in 0..2) if (b[r][c] == 0) list += r to c
        return list
    }

    fun scoreTerminal(b: Array<IntArray>, depth: Int): Int {
        return when (winner(b)) {
            1 -> 10 - depth
            2 -> depth - 10
            else -> 0
        }
    }
    fun minimax(
        b: Array<IntArray>,
        depth: Int,
        maximizing: Boolean,
        alphaIn: Int,
        betaIn: Int
    ): Pair<Int, Pair<Int, Int>?> {
        val w = winner(b)
        if (w != null || emptyCells(b).isEmpty()) {
            return scoreTerminal(b, depth) to null
        }

        var alpha = alphaIn
        var beta = betaIn
        var bestMove: Pair<Int, Int>? = null

        if (maximizing) {
            var best = Int.MIN_VALUE
            for ((r, c) in emptyCells(b)) {
                val nb = cloneBoard(b)
                nb[r][c] = 1
                val (valChild, _) = minimax(nb, depth + 1, false, alpha, beta)
                if (valChild > best) {
                    best = valChild
                    bestMove = r to c
                }
                alpha = max(alpha, best)
                if (beta <= alpha) break
            }
            return best to bestMove
        } else {
            var best = Int.MAX_VALUE
            for ((r, c) in emptyCells(b)) {
                val nb = cloneBoard(b)
                nb[r][c] = 2
                val (valChild, _) = minimax(nb, depth + 1, true, alpha, beta)
                if (valChild < best) {
                    best = valChild
                    bestMove = r to c
                }
                beta = min(beta, best)
                if (beta <= alpha) break
            }
            return best to bestMove
        }
    }
    fun bestMovesOrdered(b: Array<IntArray>): List<Pair<Int, Int>> {
        val candidates = emptyCells(b)
        return candidates
            .map { move ->
                val nb = cloneBoard(b)
                nb[move.first][move.second] = 1
                val (sc, _) = minimax(nb, 0, false, Int.MIN_VALUE, Int.MAX_VALUE)
                Triple(move, sc, nb)
            }
            .sortedByDescending { it.second }
            .map { it.first }
    }

    fun humanWinningMoves(b: Array<IntArray>): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        for ((r, c) in emptyCells(b)) {
            val nb = cloneBoard(b)
            nb[r][c] = 2
            if (winner(nb) == 2) list += r to c
        }
        return list
    }

    fun aiImmediateWinningMove(b: Array<IntArray>): Pair<Int, Int>? {
        for ((r, c) in emptyCells(b)) {
            val nb = cloneBoard(b)
            nb[r][c] = 1
            if (winner(nb) == 1) return r to c
        }
        return null
    }

    fun chooseMoveEasy(b: Array<IntArray>): Pair<Int, Int>? {
        val ordered = bestMovesOrdered(b)
        if (ordered.isEmpty()) return null
        val roll = Random.nextFloat()
        return if (roll < 0.5f) {
            if (ordered.size >= 2) ordered[1] else ordered.random()
        } else {
            ordered.first()
        }
    }

    fun chooseMoveMedium(b: Array<IntArray>): Pair<Int, Int>? {
        aiImmediateWinningMove(b)?.let { return it }
        humanWinningMoves(b).firstOrNull()?.let { threat -> return threat }
        return chooseMoveEasy(b)
    }

    fun chooseMoveHard(b: Array<IntArray>): Pair<Int, Int>? {
        val (_, move) = minimax(b, 0, true, Int.MIN_VALUE, Int.MAX_VALUE)
        return move
    }

    fun resetGame(newDifficulty: Difficulty = difficulty) {
        board = Array(3) { IntArray(3) }
        currentPlayer = 2
        moveCount = 0
        showDialog = false
        dialogText = ""
        aiThinking = false
        difficulty = newDifficulty
    }

    fun place(r: Int, c: Int, player: Int) {
        val nb = cloneBoard(board)
        nb[r][c] = player
        board = nb
        moveCount += 1
    }

    fun checkEndAndSetDialog() {
        val w = winner(board)
        if (w != null) {
            showDialog = true
            dialogText = if (w == 1) "컴퓨터 승리!" else "당신의 승리!"
        } else if (emptyCells(board).isEmpty()) {
            showDialog = true
            dialogText = "비겼어!"
        }
    }
    LaunchedEffect(currentPlayer, board, difficulty) {
        if (showDialog) return@LaunchedEffect
        if (currentPlayer == 1) {
            aiThinking = true
            val move = when (difficulty) {
                Difficulty.EASY -> chooseMoveEasy(board)
                Difficulty.MEDIUM -> chooseMoveMedium(board)
                Difficulty.HARD -> chooseMoveHard(board)
            }
            if (move != null) {
                place(move.first, move.second, 1)
            }
            aiThinking = false
            checkEndAndSetDialog()
            if (!showDialog) currentPlayer = 2
        }
    }

    @Composable
    fun eachBox(row: Int, column: Int, modifier: Modifier) {
        Box(
            modifier = modifier
                .aspectRatio(1f)
                .border(1.dp, Color(0xFF000000))
                .clickable {
                    if (showDialog) return@clickable
                    if (currentPlayer != 2) return@clickable
                    if (board[row][column] == 0) {
                        place(row, column, 2)
                        checkEndAndSetDialog()
                        if (!showDialog) currentPlayer = 1
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
        /*Text(
            modifier = Modifier.padding(top = 40f.bhp()),
            text = "냠냄뇸",
            style = DungGeunMo35,
            fontSize = 48f.isp()
        )*/
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220f.bhp())
        ){
            Image(
                painter = painterResource(R.drawable.img_diet_maintextballoon),
                contentDescription = "text balloon",
                modifier = Modifier
                    .offset(126f.wp(), 25.3f.bhp())
                    .size(232f.wp(), 90f.bhp())
            )
            Text(
                modifier = Modifier
                    .offset(133.2f.wp(), 47.6f.bhp()),
                text = "냠이가 만드는 동안 잠시만!\n25~30초 정도 걸려",
                textAlign = TextAlign.Center,
                style = DungGeunMo17,
                fontSize = 17f.isp(),
                color = Color(0xFF000000)
            )
            Box(
                modifier = Modifier.size(150f.wp(), 150f.bhp())
                    .offset(x = 131f.wp(), y = 70f.bhp()),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .matchParentSize(),
                    color = Color.Yellow
                )
                HamcoachGif(num = 1)
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 8f.bhp())
                .width(320f.wp())
                .height(60f.bhp()),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            @Composable
            fun diffButton(label: String, d: Difficulty) {
                Box(
                    modifier = Modifier
                        .width(100f.wp())
                        .height(60f.bhp())
                        .clip(RoundedCornerShape(20f.bhp()))
                        .border(
                            1.dp,
                            if (difficulty == d) Color(0xFF2E7D32) else Color(0xFF000000),
                            RoundedCornerShape(20f.bhp())
                        )
                        .background(if (difficulty == d) Color(0xFFE8F5E9) else Color.Transparent)
                        .clickable { resetGame(d) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        style = DungGeunMo24,
                        fontSize = 24f.isp(),
                        color = if (difficulty == d) Color(0xFF2E7D32) else Color.Unspecified
                    )
                }
            }
            diffButton("쉬움", Difficulty.EASY)
            diffButton("중간", Difficulty.MEDIUM)
            diffButton("어려움", Difficulty.HARD)
        }

        Box(
            modifier = Modifier
                .padding(top = 20f.bhp())
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
                .padding(top = 20f.bhp())
                .width(220f.wp())
                .height(64f.bhp())
                .clip(RoundedCornerShape(20f.bhp()))
                .border(1.dp, Color(0xFF000000), RoundedCornerShape(20f.bhp()))
                .clickable { resetGame(difficulty) },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "다시하기", style = DungGeunMo24, fontSize = 24f.isp())
        }

        if (aiThinking) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 24f.bhp())
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false
            resetGame(difficulty)
        }) {
            Box(
                modifier = Modifier
                    .size(200f.bhp())
                    .background(Color(0xFFFFFFFF))
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dialogText,
                    style = DungGeunMo24,
                    fontSize = 24f.isp()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NyamNyamNyomPreview() {
    NyamNyamNyom()
}
