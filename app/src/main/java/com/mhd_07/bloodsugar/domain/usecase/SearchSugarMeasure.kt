package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toSugarMeasure
import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo
import kotlinx.coroutines.flow.map

class SearchSugarMeasure(private val repo: SugarMeasuresRepo) {
    operator fun invoke(query: String, dateRange: Pair<Long, Long>) =
        repo.searchCurrentUserSugarMeasures(
            dateFrom = dateRange.first,
            dateTo = dateRange.second,
            search = query
        ).map { list -> list.map { it.toSugarMeasure() } }
}