package com.flaringapp.testingsimulator.admin.domain.tests

import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.SimpleAdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.DraftAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.PublishedAdminTestStatus
import com.flaringapp.testingsimulator.admin.domain.tests.models.status.ReadyToPublishAdminTestStatus
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase

class GetAdminTestsUseCase(
    private val testsRepository: AdminTestsRepository
) : GetTestsUseCase<AdminTest> {

    override suspend fun invoke(topicId: Int): CallResultList<AdminTest> {

        // TODO mock
//        return testsRepository.getTests(topicId)

        return CallResult.Success(
            listOf(
                SimpleAdminTest(1, "Test 1", 12, ReadyToPublishAdminTestStatus()),
                SimpleAdminTest(2, "Test 2", 52, DraftAdminTestStatus()),
                SimpleAdminTest(3, "Test 3", 3, PublishedAdminTestStatus())
            )
        )
    }
}