package com.flaringapp.testingsimulator.admin.domain.tasks

import com.flaringapp.testingsimulator.admin.data.repository.tasks.AdminTasksRepository
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class GetAdminTaskUseCase(
    private val repository: AdminTasksRepository
) {

    suspend operator fun invoke(taskId: Int): CallResult<AdminTaskDetailed> {
        return repository.getTask(taskId)
    }

}