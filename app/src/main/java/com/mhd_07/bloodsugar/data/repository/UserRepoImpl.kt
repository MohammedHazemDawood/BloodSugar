package com.mhd_07.bloodsugar.data.repository

import com.mhd_07.bloodsugar.data.data_source.local.AppDao
import com.mhd_07.bloodsugar.data.model.UserEntity
import com.mhd_07.bloodsugar.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow

class UserRepoImpl(private val dao: AppDao) : UserRepo {
    override suspend fun upsertUser(user: UserEntity) {
        dao.upsertUser(user)
    }

    override suspend fun deleteUserById(id: Int) {
        dao.deleteUserById(id)
    }

    override fun getAllUsers(): Flow<List<UserEntity>> =
        dao.getAllUsers()

    override fun getUserById(id: Int): Flow<UserEntity> =
        dao.getUserById(id)

}