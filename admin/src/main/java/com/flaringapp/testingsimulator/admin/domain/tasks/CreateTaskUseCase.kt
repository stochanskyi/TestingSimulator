package com.flaringapp.testingsimulator.admin.domain.tasks

import com.flaringapp.testingsimulator.admin.data.repository.tasks.AdminTasksRepository
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminBlockCreation
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class CreateTaskUseCase(
    private val repository: AdminTasksRepository
) {

    suspend operator fun invoke(
        testId: Int,
        name: String,
        blocks: List<AdminBlockCreation>
    ): CallResult<AdminTaskDetailed> {

        return repository.createTask(testId, name, blocks)

    }
}