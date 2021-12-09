package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository

class GetAdminTestsUseCase(
    private val testsRepository: AdminTestsRepository
) : GetTestsUseCase<AdminTest> {

    override suspend fun getTests(topicId: Int): CallResult<List<AdminTest>> {
        return testsRepository.getTests(topicId)
    }

}