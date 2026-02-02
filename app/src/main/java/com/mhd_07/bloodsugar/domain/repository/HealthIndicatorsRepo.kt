package com.mhd_07.bloodsugar.domain.repository

import com.mhd_07.bloodsugar.data.model.HealthIndicatorsEntity
import kotlinx.coroutines.flow.Flow

interface HealthIndicatorsRepo {
    suspend fun upsertHealthIndicator(healthIndicator: HealthIndicatorsEntity)
    suspend fun deleteHealthIndicatorById(id: Int)
    fun getCurrentUserHealthIndicators(): Flow<List<HealthIndicatorsEntity>>
    fun getHealthIndicatorById(id: Int): Flow<HealthIndicatorsEntity>
}