package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toEntity
import com.mhd_07.bloodsugar.domain.model.PressureMeasure
import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo

class UpsertPressureMeasure(private val repo: PressureMeasuresRepo) {
    suspend operator fun invoke(pressureMeasure: PressureMeasure) {
        repo.upsertPressureMeasure(pressureMeasure.toEntity())
    }
}