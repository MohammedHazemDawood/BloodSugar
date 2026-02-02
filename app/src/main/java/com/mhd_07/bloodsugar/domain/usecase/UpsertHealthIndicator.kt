package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toEntity
import com.mhd_07.bloodsugar.domain.model.HealthIndicator
import com.mhd_07.bloodsugar.domain.repository.HealthIndicatorsRepo

class UpsertHealthIndicator(private val repo : HealthIndicatorsRepo) {
    suspend operator fun invoke(healthIndicator: HealthIndicator) {
        repo.upsertHealthIndicator(healthIndicator.toEntity())
    }
}