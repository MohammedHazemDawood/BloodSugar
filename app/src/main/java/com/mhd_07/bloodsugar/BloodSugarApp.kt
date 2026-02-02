package com.mhd_07.bloodsugar

import android.app.Application
import com.mhd_07.bloodsugar.domain.di.databaseModule
import com.mhd_07.bloodsugar.domain.di.repositoriesModule
import com.mhd_07.bloodsugar.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class BloodSugarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BloodSugarApp)
            modules(databaseModule, repositoriesModule, useCasesModule)
        }
    }
}