package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase

class GetAdminTestsUseCase(
    private val testsRepository: AdminTestsRepository
) : GetTestsUseCase<AdminTest> {

    override suspend fun invoke(topicId: Int): CallResultList<AdminTest> {
        return testsRepository.getTests(topicId)
    }
}