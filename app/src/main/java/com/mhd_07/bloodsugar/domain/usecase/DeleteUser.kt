package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.domain.repository.UserRepo

class DeleteUser(private val repo: UserRepo) {
    suspend fun invoke(id: Int) {
        repo.deleteUserById(id)
    }
}