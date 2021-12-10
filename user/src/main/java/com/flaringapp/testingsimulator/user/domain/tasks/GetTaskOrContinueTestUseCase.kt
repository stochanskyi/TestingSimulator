package com.flaringapp.testingsimulator.user.domain.tasks

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tests.ContinueTestUseCase

class GetTaskOrContinueTestUseCase(
    private val getTaskUseCase: GetUserTaskUseCase,
    private val continueTestUseCase: ContinueTestUseCase,
) {

    suspend operator fun invoke(testId: Int, taskId: Int): CallResult<UserTask> {
        return getTaskUseCase(taskId)
            .onErrorSwitchSuspend { continueTestUseCase(testId) }
    }
}