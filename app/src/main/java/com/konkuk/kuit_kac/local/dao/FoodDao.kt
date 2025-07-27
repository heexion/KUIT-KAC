package com.konkuk.kuit_kac.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.konkuk.kuit_kac.local.Food


@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(foods: List<Food>)

    @Query("SELECT EXISTS(SELECT 1 FROM food LIMIT 1)")
    suspend fun hasAnyFood(): Boolean

    @Query("SELECT * FROM food WHERE name LIKE :query LIMIT 10")
    suspend fun searchFoodsByName(query: String): List<Food>

    @Query("SELECT * FROM food WHERE name = :name")
    suspend fun getFood(name: String): Food?
}



