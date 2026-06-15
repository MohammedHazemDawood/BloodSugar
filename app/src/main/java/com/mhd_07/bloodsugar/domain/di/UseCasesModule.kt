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
    factory { GetHealthIndicators(get()) }
    factory { DeleteHealthIndicator(get()) }
    factory { UpsertHealthIndicator(get()) }
    factory { GetLatestSugarMeasure(get()) }
    factory { SearchSugarMeasure(get()) }
    factory { LoginUseCase(get()) }
    factory { GetSelectedUser(get()) }
    factory { UserUseCases(get(), get(), get(), get(), get()) }
    factory { SugarMeasureUseCases(get(), get(), get(), get(), get()) }
}