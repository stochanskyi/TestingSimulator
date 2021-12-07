package com.flaringapp.testingsimulator.user.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.core.data.network.base.BaseResponse
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.LoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    //TODO change route
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): ApiResponse<LoginResponse>

}