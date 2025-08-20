package com.konkuk.kuit_kac.local.parse

import android.content.Context
import android.util.Log
import com.konkuk.kuit_kac.local.Fitness
import com.opencsv.CSVReaderBuilder
import com.opencsv.RFC4180ParserBuilder
import java.io.InputStreamReader
import kotlin.math.abs

suspend fun loadFitness(context: Context): List<Fitness> {
    val out = mutableListOf<Fitness>()
    val seenIds = HashSet<Int>()
    val seenNames = HashSet<String>()

    context.assets.open("fitness.csv").use { input ->
        val reader = CSVReaderBuilder(InputStreamReader(input, Charsets.UTF_8))
            .withCSVParser(RFC4180ParserBuilder().build())
            .build()

        var rowNum = 0
        while (true) {
            val row = reader.readNext() ?: break
            rowNum++
            if (row.isEmpty()) continue
            if (rowNum == 1 && row[0].startsWith("\uFEFF")) row[0] = row[0].removePrefix("\uFEFF")

            // Trim all fields
            val c = Array(row.size) { i -> row[i].trim() }

            if (rowNum == 1 && (c[0].equals("id", true) || c[0].equals("name", true))) continue

            val idFromFirst = c[0].toIntOrNull()
            if (idFromFirst != null && c.size >= 4) {
                val name = c[1]
                val target = c[2]
                val met = parseDouble(c[3])

                if (name.isEmpty() || target.isEmpty() || met == null) {
                    Log.w("CSV", "fitness parse fail (line $rowNum): ${c.toList()}"); continue
                }
                if (!seenIds.add(idFromFirst) || !seenNames.add(name)) continue

                out += Fitness(id = idFromFirst, name = name, targetMuscleGroup = target, metValue = met)
                continue
            }

            if (c.size >= 3) {
                val name = c[0]
                val target = c[1]
                var met: Double? = null
                var j = 2
                while (j < c.size && met == null) {
                    met = parseDouble(c[j]); j++
                }

                if (name.isEmpty() || target.isEmpty() || met == null) {
                    Log.w("CSV", "fitness parse fail (line $rowNum): ${c.toList()}"); continue
                }
                if (!seenNames.add(name)) continue
                var genId = abs(name.hashCode())
                while (!seenIds.add(genId)) genId = (genId + 1) and 0x7FFFFFFF

                out += Fitness(id = genId, name = name, targetMuscleGroup = target, metValue = met)
                continue
            }

            Log.w("CSV", "unexpected column count (line $rowNum, size=${c.size}): ${c.toList()}")
        }
    }
    return out
}

private fun parseDouble(s: String): Double? {
    val t = s.trim()
    if (t.isEmpty()) return null
    return t.replace(',', '.').toDoubleOrNull()
}
