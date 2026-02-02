package com.mhd_07.bloodsugar.domain.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mhd_07.bloodsugar.data.data_source.local.DataStoreManager
import com.mhd_07.bloodsugar.data.repository.HealthIndicatorsRepoImpl
import com.mhd_07.bloodsugar.data.repository.PressureMeasureRepoImpl
import com.mhd_07.bloodsugar.data.repository.SugarMeasureRepoImpl
import com.mhd_07.bloodsugar.data.repository.UserRepoImpl
import com.mhd_07.bloodsugar.domain.repository.HealthIndicatorsRepo
import com.mhd_07.bloodsugar.domain.repository.PressureMeasuresRepo
import com.mhd_07.bloodsugar.domain.repository.SugarMeasuresRepo
import com.mhd_07.bloodsugar.domain.repository.UserRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATASTORE_NAME = "app_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

val repositoriesModule = module {
    single<UserRepo> { UserRepoImpl(get(), get()) }
    single<SugarMeasuresRepo> { SugarMeasureRepoImpl(get(), get()) }
    single<PressureMeasuresRepo> { PressureMeasureRepoImpl(get(), get()) }
    single<HealthIndicatorsRepo> { HealthIndicatorsRepoImpl(get(), get()) }
    single { DataStoreManager(androidContext().dataStore) }
}