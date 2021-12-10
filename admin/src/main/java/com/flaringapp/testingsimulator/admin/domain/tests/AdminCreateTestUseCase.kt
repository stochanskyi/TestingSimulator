package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult

class AdminCreateTestUseCase(
    private val testsRepository: AdminTestsRepository
) {

    suspend operator fun invoke(topicId: Int): CallResult<AdminTest> {
        return testsRepository.createTest(topicId)
    }
}