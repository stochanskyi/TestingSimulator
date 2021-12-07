package com.flaringapp.testingsimulator.data.usecase.auth

import com.flaringapp.testingsimulator.data.common.call.CallResult

interface LoginUseCase {
    suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean,
    ): CallResult<Unit>
}

//TODO Should be moved to regular user presentation module and replaced with auth logic
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