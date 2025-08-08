package com.konkuk.kuit_kac.presentation.fitness.local

import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.local.dao.FitnessDao

class FitnessRepository(private val dao: FitnessDao) {
    suspend fun searchFitness(query: String) = dao.searchFitnessByName(query)

    suspend fun getFitness(name: String) = dao.getFitness(name)
}