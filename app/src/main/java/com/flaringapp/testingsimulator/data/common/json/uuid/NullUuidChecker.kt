package com.flaringapp.testingsimulator.data.common.json.uuid

import java.util.*

class NullUuidChecker private constructor() {

    companion object {
        val instance by lazy { NullUuidChecker() }

        private val nullUuid: String by lazy {
            UUID(0, 0).toString()
        }
    }

    fun isNullUuid(string: String?): Boolean = string == nullUuid

}