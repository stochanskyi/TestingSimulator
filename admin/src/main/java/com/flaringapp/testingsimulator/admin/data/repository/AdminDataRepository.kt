package com.flaringapp.testingsimulator.admin.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminDataRepository {
    fun setToken(token: String)
    fun getToken(): String?
    fun clearToken()
}

class AdminDataRepositoryImpl(
    private val dataStorage: DataStorage
) : AdminDataRepository {

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