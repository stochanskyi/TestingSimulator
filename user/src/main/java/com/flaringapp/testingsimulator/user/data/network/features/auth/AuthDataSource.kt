package com.flaringapp.testingsimulator.user.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.LoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.SignUpRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.response.LoginResponse

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): CallResult<LoginResponse>

    suspend fun signUp(signUpRequest: SignUpRequest): CallResult<LoginResponse>
}

class AuthDataSourceImpl(
    private val authApi: AuthApi
) : AuthDataSource {

    override suspend fun login(loginRequest: LoginRequest): CallResult<LoginResponse> {
        return authApi.login(loginRequest).validate()
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): CallResult<LoginResponse> {
        return authApi.createAccount(signUpRequest).validate()
    }

}