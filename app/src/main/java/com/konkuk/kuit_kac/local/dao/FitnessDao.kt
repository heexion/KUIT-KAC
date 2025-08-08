package com.konkuk.kuit_kac.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.local.Food

@Dao
interface FitnessDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(fitness: List<Fitness>)

    @Query("SELECT EXISTS(SELECT 1 FROM fitness LIMIT 1)")
    suspend fun hasAnyFitness(): Boolean

    @Query("SELECT * FROM fitness WHERE name LIKE :query LIMIT 10")
    suspend fun searchFitnessByName(query: String): List<Fitness>

    @Query("SELECT * FROM fitness WHERE name = :name")
    suspend fun getFitness(name: String): Fitness?
}
