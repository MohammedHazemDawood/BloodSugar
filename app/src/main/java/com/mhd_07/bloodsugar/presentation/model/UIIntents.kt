package com.mhd_07.bloodsugar.presentation.model

import com.mhd_07.bloodsugar.domain.model.HealthIndicator
import com.mhd_07.bloodsugar.domain.model.SugarMeasure
import com.mhd_07.bloodsugar.domain.model.User

sealed class UIIntents {
    data class DeleteUser(val id: Int) : UIIntents()
    data class DeleteSugarMeasure(val id: Int) : UIIntents()
    data class DeleteHealthIndicator(val id: Int) : UIIntents()

    data class UpsertUser(val user: User) : UIIntents()
    data class UpsertSugarMeasure(val sugarMeasure: SugarMeasure) : UIIntents()
    data class UpsertHealthIndicator(val healthIndicator: HealthIndicator) : UIIntents()
    data class SelectUser(val id: Int) : UIIntents()
    data class SelectSugarMeasure(val measure : SugarMeasure) : UIIntents()
}