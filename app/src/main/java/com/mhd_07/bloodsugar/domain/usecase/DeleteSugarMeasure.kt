package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo

class DeleteSugarMeasure(private val repo : SugarMeasuresRepo) {
    suspend operator fun invoke(id : Int) { repo.deleteSugarMeasureById(id) }
}