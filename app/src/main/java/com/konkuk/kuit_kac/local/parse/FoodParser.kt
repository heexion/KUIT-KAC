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

            // Strip BOM on first cell of first row if present
            if (rowNum == 1 && row[0].startsWith("\uFEFF")) {
                row[0] = row[0].removePrefix("\uFEFF")
            }

            if (row.size < 10) {
                Log.w("CSV", "열 개수 부족 (line $rowNum): ${row.toList()}")
                continue
            }

            // Int fields
            val id = row[0].trim().toIntOrNull()
            if (id == null) { Log.w("CSV", "id 파싱 실패 (line $rowNum): ${row.toList()}"); continue }

            val unitNum = row[3].trim().toIntOrNull()
            if (unitNum == null) { Log.w("CSV", "unit_num 파싱 실패 (line $rowNum): ${row.toList()}"); continue }

            val isProcessed = row[5].trim().toIntOrNull()
            if (isProcessed == null) { Log.w("CSV", "is_processed_food 파싱 실패 (line $rowNum): ${row.toList()}"); continue }

            // Double fields (supports "3,5" and "3.5")
            val calorie = parseDouble(row[6])
            val carbohydrate = parseDouble(row[7])
            val protein = parseDouble(row[8])
            val fat = parseDouble(row[9])

            if (calorie == null || carbohydrate == null || protein == null || fat == null) {
                Log.w("CSV", "실수 파싱 실패 (line $rowNum): ${row.toList()}")
                continue
            }

            val sugar = row.getOrNull(10)?.let { parseDouble(it) } ?: 0.0

            out += Food(
                id = id,
                name = row[1].trim(),
                unit_type = row[2].trim(),
                unit_num = unitNum,
                food_type = row[4].trim(),
                is_processed_food = isProcessed,
                calorie = calorie,
                carbohydrate = carbohydrate,
                protein = protein,
                fat = fat,
                sugar = sugar
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
