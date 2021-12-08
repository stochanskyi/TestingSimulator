package com.flaringapp.testingsimulator.user.data.storage.profile

import android.content.Context
import com.flaringapp.testingsimulator.core.data.storage.BaseDataStorage

interface UserProfileDataStorage {
    var firstName: String?
    var lastName: String?
    var email: String?
    var studying: String?
    var workPlace: String?
    var role: String?
}

private const val PREFS_NAME = "user_profile_prefs"

class UserProfileDataStorageImpl(
    context: Context
) : BaseDataStorage(context, PREFS_NAME), UserProfileDataStorage {

    override var firstName: String? by preferenceNullable("first_name")
    override var lastName: String? by preferenceNullable("last_name")
    override var email: String? by preferenceNullable("email")
    override var studying: String? by preferenceNullable("studying")
    override var workPlace: String? by preferenceNullable("work_place")
    override var role: String? by preferenceNullable("role")

}