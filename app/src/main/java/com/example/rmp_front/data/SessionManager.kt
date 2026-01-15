package com.example.rmp_front.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


object SessionManager {

    private val Context.dataStore by preferencesDataStore("session")

    private val USER_ID = stringPreferencesKey("user_id")
    private val TOKEN = stringPreferencesKey("token")

    suspend fun saveSession(context: Context, userId: String, token: String?) {
        context.dataStore.edit {
            it[USER_ID] = userId
            token?.let { t -> it[TOKEN] = t }
        }
    }

    fun userIdFlow(context: Context): Flow<String?> =
        context.dataStore.data.map { it[USER_ID] }

    suspend fun getUserId(context: Context): String? =
        context.dataStore.data.first()[USER_ID]

    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}
