package com.mhd_07.bloodsugar.domain.repository

import com.mhd_07.bloodsugar.data.model.PressureMeasureEntity
import kotlinx.coroutines.flow.Flow

interface PressureMeasuresRepo {
    suspend fun upsertPressureMeasure(pressureMeasure: PressureMeasureEntity)
    suspend fun deletePressureMeasureById(id: Int)
    fun getCurrentUserPressureMeasures(): Flow<List<PressureMeasureEntity>>
    fun getPressureMeasureById(id: Int): Flow<PressureMeasureEntity>
    fun searchUserPressureMeasures(
        dateFrom: Long = 0,
        dateTo: Long = 0,
        search: String = ""
    ): Flow<List<PressureMeasureEntity>>
    fun getLatestPressureMeasures(limit: Int = 1): Flow<List<PressureMeasureEntity>>
}