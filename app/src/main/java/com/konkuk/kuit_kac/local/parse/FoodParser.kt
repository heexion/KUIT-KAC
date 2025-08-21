import android.content.Context
import android.util.Log
import com.konkuk.kuit_kac.local.Food
import com.opencsv.CSVReaderBuilder
import com.opencsv.RFC4180ParserBuilder
import java.io.InputStreamReader

suspend fun loadFoods(context: Context): List<Food> {
    val out = mutableListOf<Food>()

    context.assets.open("food.csv").use { input ->
        val reader = CSVReaderBuilder(InputStreamReader(input, Charsets.UTF_8))
            .withCSVParser(RFC4180ParserBuilder().build())
            .build()

        var rowNum = 0
        while (true) {
            val raw = reader.readNext() ?: break
            rowNum++
            if (raw.isEmpty()) continue
            fun cell(i: Int): String {
                val s = raw.getOrNull(i) ?: return ""
                return s.trim()
                    .removePrefix("\uFEFF")
                    .trim('"')
            }

            if (rowNum == 1) {
                val c0 = cell(0).lowercase()
                val c2 = cell(2).lowercase()
                val c2Num = cell(2).toIntOrNull()

                val looksLikeHeader =
                    c0 == "name" || c2 == "unit_num" || c2Num == null

                if (looksLikeHeader) {
                    Log.i("CSV", "헤더 스킵 (line $rowNum): ${raw.toList()}")
                    continue
                }
            }

            // Need at least up to fat (index 8). sugar/score optional.
            if (raw.size < 9) {
                Log.w("CSV", "열 개수 부족 (line $rowNum): ${raw.toList()}")
                continue
            }

            val name = cell(0)
            if (name.isEmpty()) {
                Log.w("CSV", "name 없음 (line $rowNum): ${raw.toList()}")
                continue
            }

            val unitType = cell(1).ifEmpty { "개" }
            val unitNum = cell(2).toIntOrNull()
            if (unitNum == null) {
                Log.w("CSV", "unit_num 파싱 실패 (line $rowNum): ${raw.toList()}")
                continue
            }

            val foodType = cell(3)
            val isProcessed = parseBoolean(cell(4))
            if (isProcessed == null) {
                Log.w("CSV", "is_processed_food 파싱 실패 (line $rowNum): ${raw.toList()}")
                continue
            }

            val calorie = parseDouble(cell(5))
            val carbohydrate = parseDouble(cell(6))
            val protein = parseDouble(cell(7))
            val fat = parseDouble(cell(8))
            if (calorie == null || carbohydrate == null || protein == null || fat == null) {
                Log.w("CSV", "실수 파싱 실패 (line $rowNum): ${raw.toList()}")
                continue
            }

            val sugar = parseDouble(cell(9)) ?: 0.0
            val score = cell(10).toIntOrNull() ?: 0

            out += Food(
                name = name,
                unit_type = unitType,
                unit_num = unitNum,
                food_type = foodType,
                is_processed_food = isProcessed,
                calorie = calorie,
                carbohydrate = carbohydrate,
                protein = protein,
                fat = fat,
                sugar = sugar,
                score = score
            )
        }
    }
    return out
}

private fun parseDouble(s: String): Double? {
    val t = s.trim()
    if (t.isEmpty()) return null
    return t.replace(',', '.').toDoubleOrNull()
}

private fun parseBoolean(s: String): Boolean? = when (s.trim().lowercase()) {
    "1", "true", "y", "yes" -> true
    "0", "false", "n", "no" -> false
    else -> null
}
