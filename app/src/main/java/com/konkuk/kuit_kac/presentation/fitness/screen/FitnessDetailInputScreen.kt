package com.konkuk.kuit_kac.presentation.fitness.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.core.util.modifier.noRippleClickable
import com.konkuk.kuit_kac.data.request.RoutineSetsDto
import com.konkuk.kuit_kac.presentation.fitness.RoutineViewModel
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetAddCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetCountCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetDistanceCard
import com.konkuk.kuit_kac.presentation.fitness.component.FitnessSetWeightCard
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.PlanConfirmButton
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20

@Composable
fun FitnessDetailInputScreen(
    modifier: Modifier = Modifier,
    routineViewModel: RoutineViewModel = hiltViewModel(),
    navController: NavHostController,
    name :String
) {
    var selectedTab by remember { mutableStateOf("횟수") }
    val scrollState = rememberScrollState()


    val vmSets = routineViewModel.setsByExerciseName[name].orEmpty()
    val countCards = remember(name) { mutableStateListOf<String>() }
    val weightCards = remember(name) { mutableStateListOf<Pair<String, String>>() }
    val distanceCards = remember(name) { mutableStateListOf<String>() }
    var setCount by remember(name) { mutableStateOf(0) }


    LaunchedEffect(name, vmSets) {
        countCards.clear()
        weightCards.clear()
        distanceCards.clear()
        val initCount = vmSets.map { if (it.count > 0) it.count.toString() else "" }
        val initWeight = vmSets.map {
            (if (it.weightKg > 0) it.weightKg.toString() else "") to
                    (if (it.weightNum > 0) it.weightNum.toString() else "")
        }
        val initDistance = vmSets.map { if (it.distance > 0) it.distance.toString() else "" }

        val initialSetCount = maxOf(initCount.size, initWeight.size, initDistance.size, 1)

        countCards.addAll(initCount)
        weightCards.addAll(initWeight)
        distanceCards.addAll(initDistance)
        while (countCards.size < initialSetCount) countCards.add("")
        while (weightCards.size < initialSetCount) weightCards.add("" to "")
        while (distanceCards.size < initialSetCount) distanceCards.add("")

        setCount = initialSetCount
    }

    fun <T> MutableList<T>.ensureSize(size: Int, factory: () -> T) {
        while (this.size < size) this.add(factory())
    }

    fun rowComplete(i: Int): Boolean {
        val count = countCards.getOrNull(i)?.toIntOrNull() ?: 0
        val w = weightCards.getOrNull(i)?.first?.toFloatOrNull() ?: 0f
        val reps = weightCards.getOrNull(i)?.second?.toIntOrNull() ?: 0
        val dist = distanceCards.getOrNull(i)?.toFloatOrNull() ?: 0f
        val hasDistance = dist > 0f
        val hasWeight = w > 0f && reps > 0
        val hasCount = count > 0
        return hasDistance || hasWeight || hasCount
    }

    val isAllFilled = setCount > 0 && (0 until setCount).all { rowComplete(it) }
    val selectedImageRes = when (selectedTab) {
        "횟수" -> R.drawable.ic_detailcount
        "무게" -> R.drawable.ic_detailweight
        "거리" -> R.drawable.ic_detaildistance
        else -> R.drawable.ic_detailcount
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3C1), Color(0xFFFFFCEE), Color(0xFFFFF3C1))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 117f.bhp()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "냠코치",
                style = DungGeunMo20,
                fontSize = 20f.isp(),
                color = Color(0xFF713E3A),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20f.hp())
            )

            Box(
                modifier = Modifier
                    .padding(top = 24f.bhp())
                    .width(240f.wp())
                    .height(50f.bhp())
            ) {
                Image(
                    painter = painterResource(id = selectedImageRes),
                    contentDescription = "선택된 탭 이미지",
                    modifier = Modifier.fillMaxSize()
                )

                Row(modifier = Modifier.fillMaxSize()) {
                    listOf("횟수", "무게", "거리").forEach { tab ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .noRippleClickable { selectedTab = tab }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(85.2f.bhp()))

            when (selectedTab) {
                "횟수" -> {
                    for (i in 0 until setCount) {
                        val count = countCards.getOrNull(i) ?: ""
                        FitnessSetCountCard(
                            setNumber = i + 1,
                            count = count,
                            onCountChange = { newText ->
                                if (newText.all { it.isDigit() }) {
                                    countCards.ensureSize(setCount) { "" }
                                    countCards[i] = newText
                                }
                            },
                            onDeleteClick = {
                                if (i < countCards.size) countCards.removeAt(i)
                                if (i < weightCards.size) weightCards.removeAt(i)
                                if (i < distanceCards.size) distanceCards.removeAt(i)
                                setCount = (setCount - 1).coerceAtLeast(0)
                            }
                        )
                        Spacer(Modifier.height(20f.bhp()))
                    }
                }

                "무게" -> {
                    for (i in 0 until setCount) {
                        val pair = weightCards.getOrNull(i) ?: ("" to "")
                        FitnessSetWeightCard(
                            setNumber = i + 1,
                            weight = pair.first,
                            count = pair.second,
                            onWeightChange = { newWeight ->
                                if (newWeight.all { it.isDigit() || it == '.' }) {
                                    weightCards.ensureSize(setCount) { "" to "" }
                                    val cur = weightCards[i]
                                    weightCards[i] = newWeight to cur.second
                                }
                            },
                            onCountChange = { newCount ->
                                if (newCount.all { it.isDigit() }) {
                                    weightCards.ensureSize(setCount) { "" to "" }
                                    val cur = weightCards[i]
                                    weightCards[i] = cur.first to newCount
                                }
                            },
                            onDeleteClick = {
                                if (i < countCards.size) countCards.removeAt(i)
                                if (i < weightCards.size) weightCards.removeAt(i)
                                if (i < distanceCards.size) distanceCards.removeAt(i)
                                setCount = (setCount - 1).coerceAtLeast(0)
                            }
                        )
                        Spacer(Modifier.height(20f.bhp()))
                    }
                }

                "거리" -> {
                    for (i in 0 until setCount) {
                        val distance = distanceCards.getOrNull(i) ?: ""
                        FitnessSetDistanceCard(
                            setNumber = i + 1,
                            distance = distance,
                            onDistanceChange = { newText ->
                                if (newText.all { it.isDigit() || it == '.' }) {
                                    distanceCards.ensureSize(setCount) { "" }
                                    distanceCards[i] = newText
                                }
                            },
                            onDeleteClick = {
                                if (i < countCards.size) countCards.removeAt(i)
                                if (i < weightCards.size) weightCards.removeAt(i)
                                if (i < distanceCards.size) distanceCards.removeAt(i)
                                setCount = (setCount - 1).coerceAtLeast(0)
                            }
                        )
                        Spacer(Modifier.height(20f.bhp()))
                    }
                }
            }

            FitnessSetAddCard(
                onClick = {
                    setCount += 1
                    countCards.ensureSize(setCount) { "" }
                    weightCards.ensureSize(setCount) { "" to "" }
                    distanceCards.ensureSize(setCount) { "" }
                }
            )

            Spacer(modifier = Modifier.height(32f.bhp()))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 117f.bhp())
        ) {
            PlanConfirmButton(
                modifier = Modifier.padding(horizontal = 24f.wp()),
                onClick = {
                    val countList: List<Int?> =
                        countCards.map { it.toIntOrNull()?.takeIf { v -> v > 0 } }

                    data class WPair(val kg: Int, val reps: Int)
                    val weightList: List<WPair?> =
                        weightCards.map { (w, r) ->
                            val kg = w.toFloatOrNull()?.let { kotlin.math.round(it).toInt() }?.takeIf { it > 0 }
                            val reps = r.toIntOrNull()?.takeIf { it > 0 }
                            if (kg != null && reps != null) WPair(kg, reps) else null
                        }

                    val distanceList: List<Int?> =
                        distanceCards.map { it.toFloatOrNull()?.let { kotlin.math.round(it).toInt() }?.takeIf { it > 0 } }

                    val maxLen = maxOf(countList.size, weightList.size, distanceList.size)
                    val mergedFromInputs: List<RoutineSetsDto> = (0 until maxLen).map { i ->
                        val c = countList.getOrNull(i)
                        val w = weightList.getOrNull(i)
                        val d = distanceList.getOrNull(i)

                        RoutineSetsDto(
                            count = c ?: 0,
                            weightKg = w?.kg ?: 0,
                            weightNum = w?.reps ?: 0,
                            distance = d ?: 0,
                            time = if (w != null) 1.0 else 0.0,
                            setOrder = i + 1
                        )
                    }.filter { it.count > 0 || it.weightKg > 0 || it.weightNum > 0 || it.distance > 0 }

                    val existing = routineViewModel.setsByExerciseName[name].orEmpty().toMutableList()
                    val out = mutableListOf<RoutineSetsDto>()

                    val maxOut = maxOf(existing.size, mergedFromInputs.size)
                    for (i in 0 until maxOut) {
                        val old = existing.getOrNull(i)
                        val neu = mergedFromInputs.getOrNull(i)

                        val combined = when {
                            old == null && neu != null -> neu
                            old != null && neu == null -> old
                            old != null && neu != null -> old.copy(
                                count = if (neu.count > 0) neu.count else old.count,
                                weightKg = if (neu.weightKg > 0) neu.weightKg else old.weightKg,
                                weightNum = if (neu.weightNum > 0) neu.weightNum else old.weightNum,
                                distance = if (neu.distance > 0) neu.distance else old.distance,
                                time = if (neu.distance > 0) 1.0 else old.time
                            )
                            else -> null
                        }
                        combined?.let { out += it }
                    }

                    val finalList = out.mapIndexed { idx, s -> s.copy(setOrder = idx + 1) }
                    routineViewModel.addOrReplaceSetsByName(name, finalList)

                    Log.d("SetsForSubmit", "$name -> $finalList")
                    navController.popBackStack()
                },
                isAvailable = isAllFilled,
                value = "입력하기"
            )
        }
    }
}