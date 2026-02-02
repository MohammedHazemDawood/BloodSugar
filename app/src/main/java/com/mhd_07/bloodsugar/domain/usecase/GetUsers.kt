package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toUser
import com.mhd_07.bloodsugar.domain.model.User
import com.mhd_07.bloodsugar.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUsers(private val repo: UserRepo) {
    fun invoke(): Flow<List<User>> = repo.getAllUsers().map { list -> list.map { it.toUser() } }
}