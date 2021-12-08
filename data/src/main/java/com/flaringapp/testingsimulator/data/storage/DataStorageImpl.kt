package com.flaringapp.testingsimulator.data.storage

import android.content.Context
import com.flaringapp.testingsimulator.core.data.storage.BaseDataStorage
import com.flaringapp.testingsimulator.domain.storage.DataStorage

private const val PREFS_NAME = "data_prefs"

class DataStorageImpl(context: Context) : BaseDataStorage(context, PREFS_NAME), DataStorage {

    override var token: String? by preferenceNullable("token")

    override var userId: String? by preferenceNullable("user_id")
}
