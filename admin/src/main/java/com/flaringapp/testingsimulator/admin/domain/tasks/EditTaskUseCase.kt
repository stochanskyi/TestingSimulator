package com.flaringapp.testingsimulator.admin.domain.tasks

import com.flaringapp.testingsimulator.admin.data.repository.tasks.AdminTasksRepository
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminBlockCreation
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskDetailed
import com.flaringapp.testingsimulator.admin.domain.tasks.models.AdminTaskEdition
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class EditTaskUseCase(
    private val repository: AdminTasksRepository

) {

    suspend operator fun invoke(task: AdminTaskEdition): CallResult<AdminTaskDetailed> {
        return repository.editTask(task)
    }

}