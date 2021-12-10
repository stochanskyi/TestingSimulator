package com.flaringapp.testingsimulator.admin.data.network.features.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.EditTaskRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.response.AdminTaskResponse
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.network.base.validate

interface AdminTasksDataSource {

    fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse>
    fun createTask(request: EditTaskRequest): CallResult<AdminTaskResponse>

}

class AdminTasksDataSourceImpl(
    private val api: AdminTasksApi
) : AdminTasksDataSource {
    override fun createTask(request: CreateAdminTaskRequest): CallResult<AdminTaskResponse> {
        return api.createTask(request).validate()
    }

    override fun createTask(request: EditTaskRequest): CallResult<AdminTaskResponse> {
        return api.editTask(request).validate()
    }

}
