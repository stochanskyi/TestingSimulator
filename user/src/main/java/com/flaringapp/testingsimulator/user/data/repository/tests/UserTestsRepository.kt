package com.flaringapp.testingsimulator.user.data.repository.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsDataSource
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestDetailsMapper
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestMapper
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails

interface UserTestsRepository {
    suspend fun startTest(testId: Int): CallResultNothing

    suspend fun getTests(topicId: Int): CallResultList<UserTest>

    suspend fun getTestDetails(testId: Int): CallResult<UserTestDetails>
}

class UserTestsRepositoryImpl(
    private val userTestsDataSource: UserTestsDataSource,
    private val userTestMapper: UserTestMapper,
    private val userTestDetailsMapper: UserTestDetailsMapper,
) : UserTestsRepository {

    override suspend fun startTest(testId: Int): CallResultNothing {
        val request = StartTestRequest(testId)

        return userTestsDataSource.startTest(request).ignoreData()
    }

    override suspend fun getTests(topicId: Int): CallResultList<UserTest> {
        val result = userTestsDataSource.getTests(topicId)

        return result.transform { userTestMapper.mapTests(this) }
    }

    override suspend fun getTestDetails(testId: Int): CallResult<UserTestDetails> {
        val result = userTestsDataSource.getTestDetails(testId)

        return result.transform { userTestDetailsMapper.mapTestDetails(this) }
    }
}