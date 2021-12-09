package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails

class GetUserTestDetailsUseCase(
    private val testsRepository: UserTestsRepository
) {
    suspend operator fun invoke(testId: Int): CallResult<UserTestDetails> {
        //TODO mock

        return CallResult.Success(
            UserTestDetails(
                1,
                "Test 1",
                12,
                89,
                false,
                0,
                mapOf("Test data" to "test value")
            )
        )
//        return testsRepository.getTestDetails(testId)
    }
}