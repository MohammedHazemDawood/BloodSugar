package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.data_source.local.DataStoreManager
import com.mhd_07.bloodsugar.data.mapper.toUser
import com.mhd_07.bloodsugar.domain.repository.UserRepo
import kotlinx.coroutines.flow.map

class GetSelectedUser(private val repo: UserRepo) {
    operator fun invoke() = repo.currentUser.map { it?.toUser() }
}