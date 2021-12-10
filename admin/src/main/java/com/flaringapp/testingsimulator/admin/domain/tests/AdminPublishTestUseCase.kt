package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class AdminPublishTestUseCase(
    private val testsRepository: AdminTestsRepository
) : AdminChangeTestStateUseCase {

    companion object {
        private const val PUBLISH_STATE = 2
    }

    override suspend operator fun invoke(testId: Int): CallResult<AdminTestDetailed> {
        return testsRepository.changeTestState(testId, PUBLISH_STATE)
    }
}