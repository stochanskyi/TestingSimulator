package com.flaringapp.testingsimulator.user.domain.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository

class GetUserTestsUseCase(
    private val userTestsRepository: UserTestsRepository
) : GetTestsUseCase<UserTest> {

    override suspend fun invoke(topicId: Int): CallResultList<UserTest> {
        //TODO mock
//        return userTestsRepository.getTests(topicId)

        return CallResult.Success(listOf(
            UserTest(1, "Test 1", 12, 67, false),
            UserTest(2, "Test 2", 13, 90, false),
            UserTest(3, "Test 3", 2, 11, true),

            ))
    }
}