package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository

class GetUserTestsUseCase(
    private val userTestsRepository: UserTestsRepository
) : GetTestsUseCase<UserTest> {

    override suspend fun getTests(topicId: Int): CallResult<List<UserTest>> {
        return userTestsRepository.getTests(topicId)
    }

}