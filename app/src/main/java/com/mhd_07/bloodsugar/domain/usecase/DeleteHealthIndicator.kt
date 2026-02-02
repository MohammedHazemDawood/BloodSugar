package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.domain.repository.HealthIndicatorsRepo

class DeleteHealthIndicator(private val repo: HealthIndicatorsRepo) {
    suspend fun invoke(id: Int) {
        repo.deleteHealthIndicatorById(id)
    }
}