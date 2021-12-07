package com.flaringapp.testingsimulator.user.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.LoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.response.LoginResponse

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): CallResult<LoginResponse>
}

class AuthDataSourceImpl(
    private val authApi: AuthApi
) : AuthDataSource {

    override suspend fun login(loginRequest: LoginRequest): CallResult<LoginResponse> {
        return authApi.login(loginRequest).validate()
    }

}