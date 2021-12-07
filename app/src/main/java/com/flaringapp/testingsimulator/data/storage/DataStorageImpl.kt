package com.flaringapp.testingsimulator.data.storage

import android.content.Context
import com.flaringapp.testingsimulator.data.storage.common.Preferences

class DataStorageImpl(context: Context) : Preferences(context, "data_prefs"), DataStorage {

    override var token: String by StringPref("token", "")

    override var userId: String by StringPref("user_id", "")
}
