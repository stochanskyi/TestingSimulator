package com.flaringapp.testingsimulator.admin.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminDataRepository {

    var token: String?

}

class AdminDataRepositoryImpl(
    private val dataStorage: DataStorage
) : AdminDataRepository {

    override var token: String?
        get() = dataStorage.token
        set(value) {
            dataStorage.token = value
        }
}