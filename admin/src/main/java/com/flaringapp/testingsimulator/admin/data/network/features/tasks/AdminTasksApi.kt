package com.flaringapp.testingsimulator.admin.data.network.features.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import retrofit2.http.*

interface AdminTasksApi {

    @GET("Task/{taskId}")
    suspend fun getTask(
        @Path("taskId") task: Int
    ): ApiResponse<AdminTaskResponse>

    @POST("Task")
    suspend fun createTask(
        @Body request: CreateAdminTaskRequest
    ): ApiResponse<AdminTaskResponse>

    @PUT("Task")
    suspend fun editTask(
        @Body request: EditAdminTaskRequest
    ): ApiResponse<AdminTaskResponse>

}