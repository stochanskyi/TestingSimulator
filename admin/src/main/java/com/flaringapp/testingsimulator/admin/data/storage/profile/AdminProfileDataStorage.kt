package com.flaringapp.testingsimulator.admin.data.storage.profile

import android.content.Context
import com.flaringapp.testingsimulator.core.data.storage.BaseDataStorage

interface AdminProfileDataStorage {
    var firstName: String?
    var lastName: String?
    var email: String?
    var workPlace: String?
    var role: String?
}

private const val PREFS_NAME = "admin_profile_prefs"

class AdminProfileDataStorageImpl(
    context: Context
) : BaseDataStorage(context, PREFS_NAME), AdminProfileDataStorage {

    override var firstName: String? by preferenceNullable("first_name")
    override var lastName: String? by preferenceNullable("last_name")
    override var email: String? by preferenceNullable("email")
    override var workPlace: String? by preferenceNullable("work_place")
    override var role: String? by preferenceNullable("role")

}