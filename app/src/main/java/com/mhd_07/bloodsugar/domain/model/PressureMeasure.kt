package com.mhd_07.bloodsugar.domain.model

import java.time.OffsetDateTime

data class PressureMeasure(
    val id: Int = 0,
    val date: OffsetDateTime,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int?,
    val notes: String,
    val tags: List<String>,
    val secondMeasureId: Int?
)
