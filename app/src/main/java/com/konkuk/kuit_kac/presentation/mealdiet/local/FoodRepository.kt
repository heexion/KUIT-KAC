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
                id = startId + index,
                name = f.name,
                unit_type = f.unitType.ifBlank { "개" },
                unit_num = 1,
                food_type = f.foodType.ifBlank { "NORMAL_GRAIN_AND_TUBER" },
                is_processed_food = if (f.isProcessedFood) 1 else 0,
                calorie = f.calorie,
                carbohydrate = f.carbohydrate,
                protein = f.protein,
                fat = f.fat,
                sugar = f.sugar
            )
        }
        dao.insertAll(toInsert)
    }

}