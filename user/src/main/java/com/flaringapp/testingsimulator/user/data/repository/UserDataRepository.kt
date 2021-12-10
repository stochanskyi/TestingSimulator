package com.flaringapp.testingsimulator.user.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface UserDataRepository {

    var token: String?

    var remember: Boolean

}

class UserDataRepositoryImpl(
    private val dataStorage: DataStorage
) : UserDataRepository {

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