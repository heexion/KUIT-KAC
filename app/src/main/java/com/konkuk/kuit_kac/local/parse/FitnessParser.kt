package com.konkuk.kuit_kac.local.parse

import android.content.Context
import android.util.Log
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.local.Food
import java.io.BufferedReader
import java.io.InputStreamReader

fun loadFitness(context: Context): List<Fitness>{
    val inputStream = context.assets.open("fitness.csv")
    val reader = BufferedReader(InputStreamReader(inputStream))
    val fitnesses = mutableListOf<Fitness>()
    reader.readLine()
    reader.forEachLine { line->
        val tokens = line.split(",")

        try {
            val fitness = Fitness(
                name = tokens[0],
                targetMuscleGroup = tokens[1],
                metValue = tokens[2].toDoubleOrNull()?: 0.0,
                type = tokens[3].toInt()
            )
            fitnesses.add(fitness)
        } catch (e: Exception){
            Log.e("CSV", "파싱 에러: $line")
        }
    }
    return fitnesses
}
