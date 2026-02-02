package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toHealthIndicator
import com.mhd_07.bloodsugar.domain.model.HealthIndicator
import com.mhd_07.bloodsugar.domain.repository.HealthIndicatorsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHealthIndicators(private val repo: HealthIndicatorsRepo) {
    operator fun invoke(): Flow<List<HealthIndicator>> =
        repo.getCurrentUserHealthIndicators().map { list -> list.map { it.toHealthIndicator() } }

}