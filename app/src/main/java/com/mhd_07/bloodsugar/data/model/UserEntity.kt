package com.mhd_07.bloodsugar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int,
    val tall: Int,
    val weight: Int,
    val preferredUnitSystem: String,
    val preferredSugarUnit: String,
    val dangerSugarHigh: Int,
    val dangerSugarLow: Int,
)
