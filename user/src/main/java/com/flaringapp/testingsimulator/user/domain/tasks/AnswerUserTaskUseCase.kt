package com.flaringapp.testingsimulator.user.domain.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tasks.UserTasksRepository
import com.flaringapp.testingsimulator.user.domain.tasks.model.PotentialUserTask

class AnswerUserTaskUseCase(
    private val userTasksRepository: UserTasksRepository
) {

    suspend operator fun invoke(taskId: Int, answerBlockIds: List<Int>): CallResult<PotentialUserTask> {
        return userTasksRepository.answerTask(taskId, answerBlockIds)
    }
}