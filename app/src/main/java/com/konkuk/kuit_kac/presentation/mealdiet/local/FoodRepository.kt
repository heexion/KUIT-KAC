package com.konkuk.kuit_kac.presentation.mealdiet.local

import com.konkuk.kuit_kac.local.dao.FoodDao

class FoodRepository(private val dao: FoodDao) {
    suspend fun searchFood(query: String) = dao.searchFoodsByName(query)
}