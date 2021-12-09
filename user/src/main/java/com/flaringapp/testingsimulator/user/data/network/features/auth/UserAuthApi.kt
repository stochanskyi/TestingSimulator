package com.flaringapp.testingsimulator.user.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserLoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserSignUpRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.response.UserProfileWithTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthApi {

    @POST("User/login")
    suspend fun login(
        @Body request: UserLoginRequest
    ): ApiResponse<UserProfileWithTokenResponse>

    @POST("User")
    suspend fun createAccount(
        @Body request: UserSignUpRequest
    ): ApiResponse<UserProfileWithTokenResponse>

}