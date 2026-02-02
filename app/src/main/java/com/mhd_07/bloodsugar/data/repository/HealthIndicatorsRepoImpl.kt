package com.mhd_07.bloodsugar.data.repository

import com.mhd_07.bloodsugar.data.data_source.local.AppDao
import com.mhd_07.bloodsugar.data.data_source.local.DataStoreManager
import com.mhd_07.bloodsugar.data.model.HealthIndicatorsEntity
import com.mhd_07.bloodsugar.domain.repository.HealthIndicatorsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class HealthIndicatorsRepoImpl(private val dao: AppDao, private val dataStoreManager: DataStoreManager) :
    HealthIndicatorsRepo {
    override suspend fun upsertHealthIndicator(healthIndicator: HealthIndicatorsEntity) =
        dataStoreManager.userIdFlow.collect {
            if (it == -1) return@collect
            dao.upsertHealthIndicator(healthIndicator.copy(userId = it))
        }

    override suspend fun deleteHealthIndicatorById(id: Int) {
        dao.deleteHealthIndicatorById(id)
    }

    override fun getCurrentUserHealthIndicators(): Flow<List<HealthIndicatorsEntity>> = dataStoreManager.userIdFlow.flatMapLatest {
        if (it == -1) flowOf(emptyList())
        else
            dao.getAllHealthIndicators(it)
    }

    override fun getHealthIndicatorById(id: Int): Flow<HealthIndicatorsEntity> = dao.getHealthIndicatorById(id)

}