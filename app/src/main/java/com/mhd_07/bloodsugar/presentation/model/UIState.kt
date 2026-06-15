package com.mhd_07.bloodsugar.presentation.model

import com.mhd_07.bloodsugar.domain.model.HealthIndicator
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.model.User

data class UIState(
    val users: List<User> = emptyList(),
    val sugarMeasures: List<SugarMeasure> = emptyList(),
    val healthIndicators: List<HealthIndicator> = emptyList(),
    val selectedUser: User? = null,
    val latestSugarMeasure: SugarMeasure? = null,
    val searchQuery: String = "",
    val dateRange: Pair<Long, Long> = Pair(0L, 0L),
    val isSearching : Boolean = false
)

data class SearchState(
    val searchQuery: String = "",
    val dateRange: Pair<Long, Long> = Pair(0L, 0L),
    val isSearching : Boolean = false
)

data class SugarMeasuresData(
    val sugarMeasures: List<SugarMeasure> = emptyList(),
    val latestSugarMeasure: SugarMeasure? = null,
    val selectedSugarMeasure: SugarMeasure? = null,
)