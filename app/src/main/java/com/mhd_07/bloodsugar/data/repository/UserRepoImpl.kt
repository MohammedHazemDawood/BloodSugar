package com.mhd_07.bloodsugar.data.repository

import com.mhd_07.bloodsugar.data.data_source.local.AppDao
import com.mhd_07.bloodsugar.data.data_source.local.DataStoreManager
import com.mhd_07.bloodsugar.data.model.UserEntity
import com.mhd_07.bloodsugar.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class UserRepoImpl(private val dao: AppDao, private val dataStoreManager: DataStoreManager) : UserRepo {
    override suspend fun upsertUser(user: UserEntity) {
        dao.upsertUser(user).let {
            dataStoreManager.setUserId(it)
        }
    }

    override suspend fun deleteUserById(id: Int) {
        dao.deleteUserById(id)
        dataStoreManager.setUserId(-1)
    }

    override fun getAllUsers(): Flow<List<UserEntity>> =
        dao.getAllUsers()

    override fun getUserById(id: Int): Flow<UserEntity> =
        dao.getUserById(id)

    override val currentUser: Flow<UserEntity?>
        get() = dataStoreManager.userIdFlow.flatMapLatest {
            if (it == -1)
                flowOf(null)
            else
                getUserById(it)
        }
}