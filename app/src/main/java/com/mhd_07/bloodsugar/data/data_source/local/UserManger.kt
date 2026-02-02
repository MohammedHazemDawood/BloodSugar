package com.mhd_07.bloodsugar.data.data_source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManger(private val dataStore: DataStore<Preferences>) {
    private val userIdKey = intPreferencesKey("user_id")

    suspend fun setUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[userIdKey] = userId
        }
    }

    val userIdFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[userIdKey] ?: -1
    }
}