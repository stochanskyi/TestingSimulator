package com.flaringapp.testingsimulator.admin.data.repository.tasks

import com.flaringapp.testingsimulator.admin.data.network.features.tasks.AdminTasksDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.tasks.request.CreateAdminTaskRequest
import com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers.AdminAddBlockMapper
import com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers.AdminTaskDetailedMapper
import com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers.AdminTaskEditionMapper
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminBlockCreation
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskEdition
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

interface AdminTasksRepository {

    suspend fun getTask(taskId: Int): CallResult<AdminTaskDetailed>

    suspend fun createTask(
        testId: Int,
        taskName: String,
        blocks: List<AdminBlockCreation>
    ): CallResult<AdminTaskDetailed>

    suspend fun editTask(task: AdminTaskEdition): CallResult<AdminTaskDetailed>

}

class AdminTasksRepositoryImpl(
    private val adminTasksDataSource: AdminTasksDataSource,
    private val taskEditionMapper: AdminTaskEditionMapper,
    private val addBlockMapper: AdminAddBlockMapper,
    private val taskDetailedMapper: AdminTaskDetailedMapper
) : AdminTasksRepository {

    override suspend fun getTask(taskId: Int): CallResult<AdminTaskDetailed> {
        return adminTasksDataSource.getTask(taskId)
            .transform { taskDetailedMapper.mapTask(this) }
    }

    override suspend fun createTask(
        testId: Int,
        taskName: String,
        blocks: List<AdminBlockCreation>
    ): CallResult<AdminTaskDetailed> {
        val request = CreateAdminTaskRequest(
            testId = testId,
            name = taskName,
            blocks = addBlockMapper.mapToRequest(blocks)
        )

        return adminTasksDataSource.createTask(request)
            .transform { taskDetailedMapper.mapTask(this) }
    }

    override suspend fun editTask(task: AdminTaskEdition): CallResult<AdminTaskDetailed> {
        val request = taskEditionMapper.mapToRequest(task)
        return adminTasksDataSource.editTask(request)
            .transform { taskDetailedMapper.mapTask(this) }
    }
}