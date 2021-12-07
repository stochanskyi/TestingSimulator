package com.flaringapp.testingsimulator.admin.data.network.features.auth

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.request.AdminLoginRequest
import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.response.AdminLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AdminAuthApi {

    //TODO change route
    @POST("login")
    suspend fun login(@Body loginRequest: AdminLoginRequest): ApiResponse<AdminLoginResponse>

}