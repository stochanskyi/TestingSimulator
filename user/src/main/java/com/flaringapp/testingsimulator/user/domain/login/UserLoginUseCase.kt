package com.flaringapp.testingsimulator.user.domain.login

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase

class UserLoginUseCase : LoginUseCase {

    override suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean,
    ): CallResult<Unit> {
        // TODO login implement repository
        return CallResult.Success(Unit)
    }

}