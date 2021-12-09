package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails

class GetUserTestDetailsUseCase(
    private val testsRepository: UserTestsRepository
) {
    suspend operator fun invoke(testId: Int): CallResult<UserTestDetails> {
        return testsRepository.getTestDetails(testId)
    }
}