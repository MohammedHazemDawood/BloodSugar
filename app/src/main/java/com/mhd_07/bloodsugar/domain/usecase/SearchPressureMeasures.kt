package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toPressureMeasure
import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo
import kotlinx.coroutines.flow.map

class SearchPressureMeasures(private val repo: PressureMeasuresRepo) {
    operator fun invoke(query: String, dateRange: Pair<Long, Long>) =
        repo.searchUserPressureMeasures(
            dateFrom = dateRange.first,
            dateTo = dateRange.second,
            search = query
        ).map { list -> list.map { it.toPressureMeasure() } }
}