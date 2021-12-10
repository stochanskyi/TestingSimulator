package com.flaringapp.testingsimulator.domain.storage

interface DataStorage {

    var token: String?

    var userId: Int

    var remember: Boolean

}