package com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface EditProfileBehaviour {

    val isStudyingEnabled: Boolean
        get() = false

    val isWorkPlaceEnabled: Boolean
        get() = false

    val isRoleEnabled: Boolean
        get() = false

    suspend fun loadProfile(consumer: EditProfileBehaviourGetProfileConsumer): CallResultNothing

    suspend fun editProfile(
        firstName: String,
        lastName: String,
        studying: String,
        workPlace: String,
        role: String,
    ): CallResultNothing

}