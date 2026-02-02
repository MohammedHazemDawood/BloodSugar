package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo

class DeletePressureMeasure(private val repo: PressureMeasuresRepo) {
    suspend operator fun invoke(id: Int) {
        repo.deletePressureMeasureById(id)
    }
}