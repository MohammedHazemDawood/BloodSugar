package com.mhd_07.bloodsugar.data.mapper

import com.mhd_07.bloodsugar.data.model.SugarMeasureEntity
import com.mhd_07.bloodsugar.domain.model.SugarMeasure

fun SugarMeasureEntity.toSugarMeasure() = SugarMeasure(
    id = id,
    date = date,
    measure = measure,
    unit = unit,
    notes = notes,
    tags = tags
)

fun SugarMeasure.toEntity() = SugarMeasureEntity(
    id = id,
    date = date,
    measure = measure,
    unit = unit,
    notes = notes,
    tags = tags,
    userId = -1
)