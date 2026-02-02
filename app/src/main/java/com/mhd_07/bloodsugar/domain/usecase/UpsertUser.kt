package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.mapper.toEntity
import com.mhd_07.bloodsugar.domain.model.User
import com.mhd_07.bloodsugar.domain.repository.UserRepo

class UpsertUser(private val repo: UserRepo) {
    suspend operator fun invoke(user: User) {
        repo.upsertUser(user.toEntity())
    }
}