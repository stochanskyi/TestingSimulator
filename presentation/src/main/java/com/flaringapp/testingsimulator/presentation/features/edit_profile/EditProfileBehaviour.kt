package com.flaringapp.testingsimulator.presentation.features.edit_profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface EditProfileBehaviour {

    val isStudyingEnabled: Boolean
        get() = true

    val isWorkPlaceEnabled: Boolean
        get() = true

    val isRoleEnabled: Boolean
        get() = true

    suspend fun editProfile(
        firstName: String,
        lastName: String,
        studying: String,
        workPlace: String,
        role: String,
    ): CallResultNothing

}