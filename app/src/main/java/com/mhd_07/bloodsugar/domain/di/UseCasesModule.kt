package com.mhd_07.bloodsugar.domain.di

import com.mhd_07.bloodsugar.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetUsers(get()) }
    factory { GetUserById(get()) }
    factory { DeleteUser(get()) }
    factory { UpsertUser(get()) }
    factory { GetSugarMeasures(get()) }
    factory { GetSugarMeasureById(get()) }
    factory { DeleteSugarMeasure(get()) }
    factory { UpsertSugarMeasure(get()) }
    factory { GetPressureMeasures(get()) }
    factory { GetPressureMeasureById(get()) }
    factory { DeletePressureMeasure(get()) }
    factory { UpsertPressureMeasure(get()) }
    factory { GetHealthIndicators(get()) }
    factory { DeleteHealthIndicator(get()) }
    factory { UpsertHealthIndicator(get()) }
    factory { GetLatestSugarMeasure(get()) }
    factory { GetLatestPressureMeasure(get()) }
    factory { SearchSugarMeasure(get()) }
    factory { SearchPressureMeasures(get()) }
    factory { Login(get()) }
    factory { GetSelectedUser(get()) }
    factory { UserUseCases(get(), get(), get(), get(), get()) }
    factory { SugarMeasureUseCases(get(), get(), get(), get(), get()) }
    factory { PressureMeasureUseCases(get(), get(), get(), get(), get()) }
}