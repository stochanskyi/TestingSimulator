package com.flaringapp.testingsimulator.user.data.repository

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.network.features.auth.AuthDataSource
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.LoginRequest
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.models.UserLoginInfo

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): CallResult<UserLoginInfo>

}

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
    private val loginResponseMapper: LoginResponseMapper
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String,
    ): CallResult<UserLoginInfo> {
        val loginRequest = LoginRequest(email, password)

        return authDataSource.login(loginRequest).transform {
            loginResponseMapper.map(this)
        }
    }

}