package com.flaringapp.testingsimulator.user.domain.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tasks.UserTasksRepository
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask

class GetUserTaskUseCase(
    private val tasksRepository: UserTasksRepository
) {

    suspend operator fun invoke(testId: Int): CallResult<UserTask> {
        return tasksRepository.getTask(testId)
    }

}