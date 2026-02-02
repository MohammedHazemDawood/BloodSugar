package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toSugarMeasure
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSugarMeasureById(private val repo: SugarMeasuresRepo) {
    fun invoke(id: Int): Flow<SugarMeasure> =
        repo.getSugarMeasureById(id).map { it.toSugarMeasure() }
}