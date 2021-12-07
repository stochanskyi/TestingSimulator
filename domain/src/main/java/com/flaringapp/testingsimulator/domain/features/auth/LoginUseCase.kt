package com.flaringapp.testingsimulator.domain.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface LoginUseCase {
    suspend fun login(
        email: String,
        password: String,
        rememberMe: Boolean,
    ): CallResultNothing
}
