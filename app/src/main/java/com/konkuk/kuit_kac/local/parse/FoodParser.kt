package com.konkuk.kuit_kac.local.parse

import android.content.Context
import android.util.Log
import androidx.room.Query
import com.konkuk.kuit_kac.local.Food
import java.io.BufferedReader
import java.io.InputStreamReader

fun loadFood(context: Context): List<Food>{
    val inputStream = context.assets.open("food.csv")
    val reader = BufferedReader(InputStreamReader(inputStream))
    val foods = mutableListOf<Food>()
    reader.readLine()
    reader.forEachLine { line->
        val tokens = line.split(",")

        try {
            val food = Food(
                name = tokens[0],
                calorie = tokens[1].toDoubleOrNull() ?: 0.0,
                protein = tokens[2].toDoubleOrNull() ?: 0.0,
                fat = tokens[3].toDoubleOrNull() ?: 0.0,
                carb = tokens[4].toDoubleOrNull() ?: 0.0

            )
            foods.add(food)
        } catch (e: Exception){
            Log.e("CSV", "파싱 에러: $line")
        }
    }
    return foods
}
