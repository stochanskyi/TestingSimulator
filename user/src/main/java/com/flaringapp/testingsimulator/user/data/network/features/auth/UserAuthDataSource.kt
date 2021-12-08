package com.flaringapp.testingsimulator.user.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserLoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserSignUpRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.response.UserProfileWithTokenResponse

interface UserAuthDataSource {

    suspend fun login(
        request: UserLoginRequest
    ): CallResult<UserProfileWithTokenResponse>

    suspend fun signUp(
        request: UserSignUpRequest
    ): CallResult<UserProfileWithTokenResponse>

}

class UserAuthDataSourceImpl(
    private val api: UserAuthApi
) : UserAuthDataSource {

    override suspend fun login(
        request: UserLoginRequest
    ): CallResult<UserProfileWithTokenResponse> {
        return api.login(request).validate()
    }

    override suspend fun signUp(
        request: UserSignUpRequest
    ): CallResult<UserProfileWithTokenResponse> {
        return api.createAccount(request).validate()
    }
}