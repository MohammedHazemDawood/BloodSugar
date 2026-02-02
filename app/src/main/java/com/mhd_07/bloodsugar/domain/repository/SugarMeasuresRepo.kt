package com.mhd_07.bloodsugar.domain.repository

import com.mhd_07.bloodsugar.data.model.SugarMeasureEntity
import kotlinx.coroutines.flow.Flow

interface SugarMeasuresRepo {
    suspend fun upsertSugarMeasure(sugarMeasure: SugarMeasureEntity)
    suspend fun deleteSugarMeasureById(id: Int)
    fun getCurrentUserSugarMeasures(): Flow<List<SugarMeasureEntity>>
    fun getSugarMeasureById(id: Int): Flow<SugarMeasureEntity>
    fun searchCurrentUserSugarMeasures(
        dateFrom: Long = 0,
        dateTo: Long = 0,
        search: String = ""
    ): Flow<List<SugarMeasureEntity>>

    fun getLatestSugarMeasures(limit: Int = 1): Flow<List<SugarMeasureEntity>>
}