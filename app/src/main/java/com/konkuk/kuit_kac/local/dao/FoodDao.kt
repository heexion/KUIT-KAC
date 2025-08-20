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

    @Query("""
        SELECT * FROM food
        WHERE name >= :from AND name <= :to
        ORDER BY name
        LIMIT 10
    """)
    suspend fun searchFoodsByRange(from: String, to: String): List<Food>

    @Query("SELECT * FROM food WHERE name = :name")
    suspend fun getFood(name: String): Food?
}

object HangulSearch {

    private const val HANGUL_BASE = 0xAC00
    private const val CHOSEONG_COUNT = 19
    private const val JUNG_COUNT = 21
    private const val JONG_COUNT = 28
    private const val SYLS_PER_CHOSEONG = JUNG_COUNT * JONG_COUNT

    private val CHOSEONG = charArrayOf(
        'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'
    )

    fun consonantRange(ch: Char): Pair<String, String>? {
        val idx = CHOSEONG.indexOf(ch)
        if (idx == -1) return null
        val start = HANGUL_BASE + idx * SYLS_PER_CHOSEONG
        val endInclusive = HANGUL_BASE + (idx + 1) * SYLS_PER_CHOSEONG - 1
        return String(intArrayOf(start), 0, 1) to String(intArrayOf(endInclusive), 0, 1)
    }
    fun prefixRange(prefix: String): Pair<String, String> {
        if (prefix.isEmpty()) return "" to "\uFFFF"
        return prefix to (prefix + '\uFFFF')
    }
}


