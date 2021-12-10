package com.flaringapp.testingsimulator.admin.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminDataRepository {

    var token: String?

    var remember: Boolean

}

class AdminDataRepositoryImpl(
    private val dataStorage: DataStorage
) : AdminDataRepository {

    override var token: String?
        get() = dataStorage.token
        set(value) {
            dataStorage.token = value
        }

    override var remember: Boolean
        get() = dataStorage.remember
        set(value) {
            dataStorage.remember = value
        }
}