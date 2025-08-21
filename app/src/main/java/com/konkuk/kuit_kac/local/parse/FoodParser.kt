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
            val row = reader.readNext() ?: break
            rowNum++
            if (row.isEmpty()) continue

            if (rowNum == 1 && row[0].startsWith("\uFEFF")) {
                row[0] = row[0].removePrefix("\uFEFF")
            }

            if (rowNum == 1 && row[0].trim().equals("name", ignoreCase = true)) continue

            if (row.size < 9) {
                Log.w("CSV", "열 개수 부족 (line $rowNum): ${row.toList()}")
                continue
            }

            val name = row[0].trim()
            if (name.isEmpty()) {
                Log.w("CSV", "name 없음 (line $rowNum): ${row.toList()}")
                continue
            }

            val unitType = row.getOrNull(1)?.trim().takeUnless { it.isNullOrEmpty() } ?: "개"
            val unitNum = row.getOrNull(2)?.trim()?.toIntOrNull()
            if (unitNum == null) {
                Log.w("CSV", "unit_num 파싱 실패 (line $rowNum): ${row.toList()}")
                continue
            }

            val foodType = row.getOrNull(3)?.trim().orEmpty()
            val isProcessed = row.getOrNull(4)?.let { parseBoolean(it) }
            if (isProcessed == null) {
                Log.w("CSV", "is_processed_food 파싱 실패 (line $rowNum): ${row.toList()}")
                continue
            }

            val calorie = row.getOrNull(5)?.let { parseDouble(it) }
            val carbohydrate = row.getOrNull(6)?.let { parseDouble(it) }
            val protein = row.getOrNull(7)?.let { parseDouble(it) }
            val fat = row.getOrNull(8)?.let { parseDouble(it) }
            if (calorie == null || carbohydrate == null || protein == null || fat == null) {
                Log.w("CSV", "실수 파싱 실패 (line $rowNum): ${row.toList()}")
                continue
            }

            val sugar = row.getOrNull(9)?.let { parseDouble(it) } ?: 0.0
            val score = row.getOrNull(10)?.trim()?.toIntOrNull() ?: 0

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
