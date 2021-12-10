package com.flaringapp.testingsimulator.admin.data.network.features.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate

interface AdminTasksDataSource {

    suspend fun getTask(taskId: Int): CallResult<AdminTaskResponse>

    suspend fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse>

    suspend fun editTask(request: EditAdminTaskRequest): CallResult<AdminTaskResponse>

}

class AdminTasksDataSourceImpl(
    private val api: AdminTasksApi
) : AdminTasksDataSource {

    override suspend fun getTask(taskId: Int): CallResult<AdminTaskResponse> {
        return api.getTask(taskId).validate()
    }

    override suspend fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse> {
        return api.createTask(request).validate()
    }

    override suspend fun editTask(request: EditAdminTaskRequest): CallResult<AdminTaskResponse> {
        return api.editTask(request).validate()
    }

}
