package com.mhd_07.bloodsugar.domain.model

data class HealthIndicator(
    val id: Int = 0,
    val name: String,
    val value: Double,
    val unit: String,
    val notes: String
)
