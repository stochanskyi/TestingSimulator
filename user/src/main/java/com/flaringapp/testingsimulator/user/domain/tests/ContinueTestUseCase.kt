package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tasks.UserTasksRepository
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask

class ContinueTestUseCase(
    private val testsRepository: UserTestsRepository,
    private val tasksRepository: UserTasksRepository,
) {

    suspend operator fun invoke(testId: Int): CallResult<UserTask> {
        return testsRepository.continueTest(testId)
            .doOnSuccessSuspend { tasksRepository.saveTask(it) }
    }
}