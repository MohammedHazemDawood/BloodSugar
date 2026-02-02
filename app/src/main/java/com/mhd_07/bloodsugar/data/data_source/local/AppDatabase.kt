package com.mhd_07.bloodsugar.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mhd_07.bloodsugar.data.model.*

@Database(
    entities = [UserEntity::class, SugarMeasureEntity::class, PressureMeasureEntity::class, HealthIndicatorsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}