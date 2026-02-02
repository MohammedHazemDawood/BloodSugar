package com.mhd_07.bloodsugar.domain.usecase

data class SugarMeasureUseCases(
    val getSugarMeasures: GetSugarMeasures,
    val deleteSugarMeasure: DeleteSugarMeasure,
    val upsertSugarMeasure: UpsertSugarMeasure,
    val getLatestSugarMeasure: GetLatestSugarMeasure,
    val searchSugarMeasure: SearchSugarMeasure,
)
