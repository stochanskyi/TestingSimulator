package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class AdminReadyToPublishTestUseCase(
    private val testsRepository: AdminTestsRepository
) {

    companion object {
        private const val READY_TO_PUBLISH_STATE = 1
    }

    suspend operator fun invoke(testId: Int): CallResult<AdminTestDetailed> {
        return testsRepository.changeTestState(testId, READY_TO_PUBLISH_STATE)
    }
}