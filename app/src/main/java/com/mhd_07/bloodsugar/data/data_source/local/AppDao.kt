package com.mhd_07.bloodsugar.data.data_source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mhd_07.bloodsugar.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Upsert
    suspend fun upsertUser(user: UserEntity) : Int

    @Upsert
    suspend fun upsertSugarMeasure(sugarMeasure: SugarMeasureEntity)

    @Upsert
    suspend fun upsertPressureMeasure(pressureMeasure: PressureMeasureEntity)

    @Upsert
    suspend fun upsertHealthIndicator(healthIndicator: HealthIndicatorsEntity)


    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Flow<UserEntity>

    @Query("SELECT * FROM sugar_measures WHERE userId = :userId")
    fun getAllUserSugarMeasures(userId: Int): Flow<List<SugarMeasureEntity>>

    @Query("SELECT * FROM pressure_measures WHERE userId = :userId")
    fun getAllUserPressureMeasures(userId: Int): Flow<List<PressureMeasureEntity>>

    @Query("SELECT * FROM health_indicators WHERE userId = :userId")
    fun getAllHealthIndicators(userId: Int): Flow<List<HealthIndicatorsEntity>>

    @Query("SELECT * FROM health_indicators WHERE id = :id")
    fun getHealthIndicatorById(id: Int): Flow<HealthIndicatorsEntity>

    @Query("SELECT * FROM sugar_measures WHERE id = :id")
    fun getSugarMeasureById(id: Int): Flow<SugarMeasureEntity>

    @Query("SELECT * FROM pressure_measures WHERE id = :id")
    fun getPressureMeasureById(id: Int): Flow<PressureMeasureEntity>

    @Query("SELECT * FROM sugar_measures WHERE userId = :userId AND (date between :dateFrom AND :dateTo OR notes LIKE '%' || :search || '%' OR tags LIKE '%' || :search || '%') ORDER BY date DESC")
    fun searchSugarMeasures(
        userId: Int,
        dateFrom: Long = 0,
        dateTo: Long = 0,
        search: String = ""
    ): Flow<List<SugarMeasureEntity>>

    @Query("SELECT * FROM pressure_measures WHERE userId = :userId AND (date between :dateFrom AND :dateTo  OR notes LIKE '%' || :search || '%' OR tags LIKE '%' || :search || '%') ORDER BY date DESC")
    fun searchPressureMeasures(
        userId: Int,
        dateFrom: Long = 0,
        dateTo: Long = 0,
        search: String = ""
    ): Flow<List<PressureMeasureEntity>>

    @Query("SELECT * FROM sugar_measures WHERE userId = :userId Order By date DESC LIMIT :limit")
    fun getLatestSugarMeasures(userId: Int, limit: Int = 1): Flow<List<SugarMeasureEntity>>

    @Query("SELECT * FROM pressure_measures WHERE userId = :userId Order By date DESC LIMIT :limit")
    fun getLatestPressureMeasures(userId: Int, limit: Int = 1): Flow<List<PressureMeasureEntity>>


    @Query("DELETE FROM users WHERE id = :id")
    suspend fun deleteUserById(id: Int)

    @Query("DELETE FROM sugar_measures WHERE id = :id")
    suspend fun deleteSugarMeasureById(id: Int)

    @Query("DELETE FROM pressure_measures WHERE id = :id")
    suspend fun deletePressureMeasureById(id: Int)

    @Query("DELETE FROM health_indicators WHERE id = :id")
    suspend fun deleteHealthIndicatorById(id: Int)
}
