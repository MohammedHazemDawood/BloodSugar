package com.mhd_07.bloodsugar.data.mapper

import com.mhd_07.bloodsugar.data.model.PressureMeasureEntity
import com.mhd_07.bloodsugar.domain.model.PressureMeasure

fun PressureMeasure.toEntity() = PressureMeasureEntity(
    id = id,
    date = date,
    systolic = systolic,
    diastolic = diastolic,
    pulse = pulse,
    notes = notes,
    userId = -1,
    tags = tags,
    secondMeasureId = secondMeasureId
)

fun PressureMeasureEntity.toPressureMeasure() = PressureMeasure(
    id = id,
    date = date,
    systolic = systolic,
    diastolic = diastolic,
    pulse = pulse,
    notes = notes,
    tags = tags,
    secondMeasureId = secondMeasureId
)