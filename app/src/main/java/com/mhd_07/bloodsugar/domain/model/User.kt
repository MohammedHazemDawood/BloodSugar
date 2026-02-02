package com.mhd_07.bloodsugar.domain.model

data class User(
    val id: Int,
    val name : String,
    val age: Int,
    val tall: Int,
    val weight: Int,
    val preferredUnitSystem: String,
    val preferredSugarUnit: String,
    val dangerSugarHigh: Int,
    val dangerSugarLow: Int,
)
