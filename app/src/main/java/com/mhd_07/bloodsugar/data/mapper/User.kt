package com.mhd_07.bloodsugar.data.mapper

import com.mhd_07.bloodsugar.data.model.UserEntity
import com.mhd_07.bloodsugar.domain.model.User

fun UserEntity.toUser(): User = User(
    id = id,
    name = name,
    age = age,
    tall =  tall,
    weight = weight,
    preferredUnitSystem = preferredUnitSystem,
    preferredSugarUnit = preferredSugarUnit,
    dangerSugarHigh = dangerSugarHigh,
    dangerSugarLow = dangerSugarLow,
)

fun User.toEntity(): UserEntity = UserEntity(
    id= id,
    name = name,
    age = age,
    tall = tall,
    weight = weight,
    preferredUnitSystem = preferredUnitSystem,
    preferredSugarUnit = preferredSugarUnit,
    dangerSugarHigh = dangerSugarHigh,
    dangerSugarLow = dangerSugarLow,
)