package com.flaringapp.testingsimulator.user.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface UserDataRepository {
    fun setToken(token: String)
    fun getToken(): String?
    fun clearToken()
}

class UserDataRepositoryImpl(
    private val dataStorage: DataStorage
) : UserDataRepository {

    override fun setToken(token: String) {
        dataStorage.token = token
    }

    override fun getToken(): String? {
        return dataStorage.token
    }

    override fun clearToken() {
        dataStorage.token = null
    }

}