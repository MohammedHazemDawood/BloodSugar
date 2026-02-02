package com.mhd_07.bloodsugar.domain.di

import android.content.Context
import androidx.room.Room
import com.mhd_07.bloodsugar.data.data_source.local.AppDatabase
import org.koin.dsl.module

private fun provideDatabase(context: Context): AppDatabase =
    Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration(false)
        .build()

private fun provideDao(database: AppDatabase) = database.appDao()

val databaseModule = module {
    single { provideDatabase(get()) }
    single { provideDao(get()) }
}