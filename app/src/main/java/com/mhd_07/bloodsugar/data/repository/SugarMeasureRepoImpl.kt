package com.mhd_07.bloodsugar.data.repository

import com.mhd_07.bloodsugar.data.data_source.local.AppDao
import com.mhd_07.bloodsugar.data.data_source.local.UserManger
import com.mhd_07.bloodsugar.data.model.SugarMeasureEntity
import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class SugarMeasureRepoImpl(private val dao: AppDao, private val userManger: UserManger) :
    SugarMeasuresRepo {
    override suspend fun upsertSugarMeasure(sugarMeasure: SugarMeasureEntity) {
        userManger.userIdFlow.collect {
            if (it == -1) return@collect
            dao.upsertSugarMeasure(sugarMeasure.copy(userId = it))
        }
    }

    override suspend fun deleteSugarMeasureById(id: Int) {
        dao.deleteSugarMeasureById(id)
    }

    override fun getCurrentUserSugarMeasures(): Flow<List<SugarMeasureEntity>> =
        userManger.userIdFlow.flatMapLatest {
            if (it == -1) flowOf(emptyList())
            else
                dao.getAllUserSugarMeasures(it)
        }

    override fun getSugarMeasureById(id: Int): Flow<SugarMeasureEntity> = dao.getSugarMeasureById(id)

    override fun searchCurrentUserSugarMeasures(
        dateFrom: Long,
        dateTo: Long,
        search: String
    ): Flow<List<SugarMeasureEntity>> = userManger.userIdFlow.flatMapLatest {
        if (it == -1) flowOf(emptyList())
        else
            dao.searchSugarMeasures(it, dateFrom, dateTo, search)
    }

    override fun getLatestSugarMeasures(limit: Int): Flow<List<SugarMeasureEntity>> = userManger.userIdFlow.flatMapLatest {
        if (it == -1) flowOf(emptyList())
        else
            dao.getLatestSugarMeasures(it, limit)
    }
}