package com.flaringapp.testingsimulator.admin.data.network.features.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AdminTasksApi {

    @POST("Task")
    fun createTask(@Body request: CreateAdminTaskRequest): ApiResponse<AdminTaskResponse>

    @PUT("Task")
    fun editTask(@Body request: EditAdminTaskRequest): ApiResponse<AdminTaskResponse>

}