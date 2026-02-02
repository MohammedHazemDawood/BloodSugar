package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toEntity
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo

class UpsertSugarMeasure(private val repo: SugarMeasuresRepo) {
    suspend fun invoke(sugarMeasure: SugarMeasure){
        repo.upsertSugarMeasure(sugarMeasure.toEntity())
    }
}