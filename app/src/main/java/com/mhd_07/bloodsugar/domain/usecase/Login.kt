package com.mhd_07.bloodsugar.domain.usecase

import com.mhd_07.bloodsugar.data.data_source.local.DataStoreManager

class Login(private val manager : DataStoreManager) {
    suspend operator fun invoke(id : Int) {
        manager.setUserId(id)
    }
}