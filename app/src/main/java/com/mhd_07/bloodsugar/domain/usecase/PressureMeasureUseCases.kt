package com.mhd_07.bloodsugar.domain.usecase

data class PressureMeasureUseCases(
    val getPressureMeasures: GetPressureMeasures,
    val deletePressureMeasure: DeletePressureMeasure,
    val upsertPressureMeasure: UpsertPressureMeasure,
    val getLatestPressureMeasure: GetLatestPressureMeasure,
    val searchPressureMeasures: SearchPressureMeasures,
)