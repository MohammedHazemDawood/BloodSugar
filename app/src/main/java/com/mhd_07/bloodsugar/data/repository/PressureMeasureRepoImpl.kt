package com.mhd_07.bloodsugar.data.repository

import com.mhd_07.bloodsugar.data.data_source.local.AppDao
import com.mhd_07.bloodsugar.data.data_source.local.UserManger
import com.mhd_07.bloodsugar.data.model.PressureMeasureEntity
import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class PressureMeasureRepoImpl(private val dao: AppDao, private val userManger: UserManger) :
    PressureMeasuresRepo {
    override suspend fun upsertPressureMeasure(pressureMeasure: PressureMeasureEntity) {
        userManger.userIdFlow.collect {
            if (it == -1) return@collect
            dao.upsertPressureMeasure(pressureMeasure.copy(userId = it))
        }
    }

    override suspend fun deletePressureMeasureById(id: Int) {
        dao.deletePressureMeasureById(id)
    }

    override fun getCurrentUserPressureMeasures(): Flow<List<PressureMeasureEntity>> =
        userManger.userIdFlow.flatMapLatest {
            if (it == -1) flowOf(emptyList())
            else
                dao.getAllUserPressureMeasures(it)
        }

    override fun getPressureMeasureById(id: Int): Flow<PressureMeasureEntity> =
        dao.getPressureMeasureById(id)

    override fun searchUserPressureMeasures(
        dateFrom: Long,
        dateTo: Long,
        search: String
    ): Flow<List<PressureMeasureEntity>> = userManger.userIdFlow.flatMapLatest {
        if (it == -1) flowOf(emptyList())
        else
            dao.searchPressureMeasures(it, dateFrom, dateTo, search)
    }


    override fun getLatestPressureMeasures(limit: Int): Flow<List<PressureMeasureEntity>> = userManger.userIdFlow.flatMapLatest {
        if (it == -1) flowOf(emptyList())
        else
            dao.getLatestPressureMeasures(it, limit)
    }
}