package com.mhd_07.bloodsugar.domain.repository

import com.mhd_07.bloodsugar.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun upsertUser(user: UserEntity)
    suspend fun deleteUserById(id: Int)
    fun getAllUsers(): Flow<List<UserEntity>>
    fun getUserById(id: Int): Flow<UserEntity>
}