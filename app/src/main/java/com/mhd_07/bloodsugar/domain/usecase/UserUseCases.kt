package com.mhd_07.bloodsugar.domain.usecase

data class UserUseCases(
    val getUsers: GetUsers,
    val deleteUser: DeleteUser,
    val upsertUser: UpsertUser,
    val getSelectedUser: GetSelectedUser,
    val login: Login,
)