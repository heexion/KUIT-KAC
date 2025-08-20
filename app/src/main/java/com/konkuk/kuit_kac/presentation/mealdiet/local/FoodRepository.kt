package com.konkuk.kuit_kac.presentation.mealdiet.local

import com.konkuk.kuit_kac.data.response.AiFoodResponse
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.local.dao.FoodDao
import com.konkuk.kuit_kac.local.dao.HangulSearch

class FoodRepository(private val dao: FoodDao) {
    suspend fun searchFood(query: String) = dao.searchFoodsByName(query)

    suspend fun getFood(name: String) = dao.getFood(name)
    suspend fun searchSmart(input: String): List<Food> {
        val q = input.trim()
        if (q.isEmpty()) return emptyList()

        if (q.length == 1) {
            HangulSearch.consonantRange(q.first())?.let { (from, to) ->
                return dao.searchFoodsByRange(from, to)
            }
        }
        val (from, to) = HangulSearch.prefixRange(q)
        return dao.searchFoodsByRange(from, to)
    }
    suspend fun addNewAiFoods(apiFoods: List<AiFoodResponse>) {
        val startId = (dao.getMaxId() ?: 0) + 1

        val toInsert = apiFoods.mapIndexed { index, f ->
            Food(
                id = startId + index,                    // sequential id
                name = f.name,
                unit_type = f.unitType.ifBlank { "개" }, // default if blank
                unit_num = 1,                            // not provided → pick sensible default
                food_type = f.foodType,
                is_processed_food = if (f.isProcessedFood) 1 else 0,
                calorie = f.calorie,
                carbohydrate = 10.0,                     // defaults per your spec
                protein = 10.0,
                fat = 10.0,
                sugar = 0.0
            )
        }
        dao.insertAll(toInsert)
    }

}