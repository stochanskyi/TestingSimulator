package com.flaringapp.testingsimulator.user.data.repository

import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface UserDataRepository {

    var token: String?

}

class UserDataRepositoryImpl(
    private val dataStorage: DataStorage
) : UserDataRepository {

    override var token: String?
        get() = dataStorage.token
        set(value) {
            dataStorage.token = value
        }
}