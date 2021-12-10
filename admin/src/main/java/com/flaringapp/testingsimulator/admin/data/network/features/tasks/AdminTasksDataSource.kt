package com.flaringapp.testingsimulator.admin.data.network.features.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate

interface AdminTasksDataSource {

    fun getTask(taskId: Int): CallResult<AdminTaskResponse>

    fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse>

    fun editTask(request: EditAdminTaskRequest): CallResult<AdminTaskResponse>

}

class AdminTasksDataSourceImpl(
    private val api: AdminTasksApi
) : AdminTasksDataSource {

    override fun getTask(taskId: Int): CallResult<AdminTaskResponse> {
        return api.getTask(taskId).validate()
    }

    override fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse> {
        return api.createTask(request).validate()
    }

    override fun editTask(request: EditAdminTaskRequest): CallResult<AdminTaskResponse> {
        return api.editTask(request).validate()
    }

}
