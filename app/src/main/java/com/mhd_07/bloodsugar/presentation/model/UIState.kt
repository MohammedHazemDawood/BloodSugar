package com.mhd_07.bloodsugar.presentation.model

import com.mhd_07.bloodsugar.domain.model.HealthIndicator
import com.mhd_07.bloodsugar.domain.model.PressureMeasure
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.model.User

data class UIState(
    val users: List<User> = emptyList(),
    val sugarMeasures: List<SugarMeasure> = emptyList(),
    val pressureMeasures: List<PressureMeasure> = emptyList(),
    val healthIndicators: List<HealthIndicator> = emptyList(),
    val selectedUser: User? = null,
    val latestSugarMeasure: SugarMeasure? = null,
    val latestPressureMeasure: PressureMeasure? = null,
    val searchQuery: String = "",
    val dateRange: Pair<Long, Long> = Pair(0L, 0L),
    val searchType : SearchType = SearchType.SUGAR,
    val isSearching : Boolean = false
)

data class SearchState(
    val searchQuery: String = "",
    val dateRange: Pair<Long, Long> = Pair(0L, 0L),
    val searchType : SearchType = SearchType.SUGAR,
    val isSearching : Boolean = false
)

data class SugarMeasuresData(
    val sugarMeasures: List<SugarMeasure> = emptyList(),
    val latestSugarMeasure: SugarMeasure? = null,
    val selectedSugarMeasure: SugarMeasure? = null,
)

data class PressureMeasuresData(
    val pressureMeasures: List<PressureMeasure> = emptyList(),
    val latestPressureMeasure: PressureMeasure? = null,
    val selectedPressureMeasure: PressureMeasure? = null,
)


enum class SearchType{
    SUGAR,
    PRESSURE
}