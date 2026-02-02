package com.mhd_07.bloodsugar.data.mapper

import com.mhd_07.bloodsugar.data.model.HealthIndicatorsEntity
import com.mhd_07.bloodsugar.domain.model.HealthIndicator

fun HealthIndicator.toEntity() = HealthIndicatorsEntity(
    id = id,
    name = name,
    value = value,
    unit = unit,
    notes = notes,
    userId = -1
)

fun HealthIndicatorsEntity.toHealthIndicator() = HealthIndicator(
    id = id,
    name = name,
    value = value,
    unit = unit,
    notes = notes,
)