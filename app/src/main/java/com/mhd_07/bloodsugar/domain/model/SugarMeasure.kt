package com.mhd_07.bloodsugar.domain.model

import java.time.OffsetDateTime

data class SugarMeasure(
    val id: Int = 0,
    val date: OffsetDateTime,
    val measure: Double,
    val unit: String,
    val notes: String,
    val tags: List<String>
)
