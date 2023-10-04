package com.kashyap.datastoredemo

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map

class UserDataStore(context: Context) {

    private val dataStore = context.createDataStore(name = "DataStoreDemo_user_prefs")


    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USERNAME_KEY")
        val AGE_KEY = preferencesKey<Int>("AGE_KEY")
    }


    suspend fun storeUser(userName: String, age: Int) = dataStore.edit {
        it[USER_NAME_KEY] = userName
        it[AGE_KEY] = age
    }

    val userAgeFlow: Flow<Int> = dataStore.data.map {
        it[AGE_KEY] ?: 0
    }
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

}