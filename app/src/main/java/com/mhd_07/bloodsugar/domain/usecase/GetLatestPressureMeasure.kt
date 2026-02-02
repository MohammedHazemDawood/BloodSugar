package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toPressureMeasure
import com.mhd_07.bloodsugar.domain.model.PressureMeasure
import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLatestPressureMeasure(private val repo: PressureMeasuresRepo) {
    fun invoke(userId: Int): Flow<PressureMeasure?> =
        repo.getLatestPressureMeasures(userId).map { it.firstOrNull()?.toPressureMeasure() }
}