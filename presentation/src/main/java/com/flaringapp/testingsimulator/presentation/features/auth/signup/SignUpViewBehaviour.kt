package com.flaringapp.testingsimulator.presentation.features.auth.signup

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface SignUpViewBehaviour {

    val isStudyingAtEnabled: Boolean get() = true

    val isWorkPlaceEnabled: Boolean get() = true

    val isRoleEnabled: Boolean get() = true

    suspend fun createAccount(
        email: String,
        firstName: String,
        lastName: String,
        studyingAt: String,
        workPlace: String,
        role: String,
        password: String,
        repeatPassword: String,
    ): CallResultNothing

}