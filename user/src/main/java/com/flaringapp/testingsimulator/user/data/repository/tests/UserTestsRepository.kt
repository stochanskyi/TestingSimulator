package com.flaringapp.testingsimulator.user.data.repository.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsDataSource
import com.flaringapp.testingsimulator.user.data.repository.tasks.mapper.UserTaskMapper
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestDetailsMapper
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestMapper
import com.flaringapp.testingsimulator.user.domain.tasks.model.UserTask
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails

interface UserTestsRepository {

    suspend fun getTests(topicId: Int): CallResultList<UserTest>

    suspend fun getTestDetails(testId: Int): CallResult<UserTestDetails>

    suspend fun startTest(testId: Int): CallResult<UserTask>

    suspend fun continueTest(testId: Int): CallResult<UserTask>

}

class UserTestsRepositoryImpl(
    private val userTestsDataSource: UserTestsDataSource,
    private val userTestMapper: UserTestMapper,
    private val userTaskMapper: UserTaskMapper,
    private val userTestDetailsMapper: UserTestDetailsMapper,
) : UserTestsRepository {

    override suspend fun getTests(topicId: Int): CallResultList<UserTest> {
        return userTestsDataSource.getTests(topicId)
            .transform { userTestMapper.mapTests(this) }
    }

    override suspend fun getTestDetails(testId: Int): CallResult<UserTestDetails> {
        return userTestsDataSource.getTestDetails(testId)
            .transform { userTestDetailsMapper.mapTestDetails(this) }
    }

    override suspend fun startTest(testId: Int): CallResult<UserTask> {
        val request = StartTestRequest(testId)
        return userTestsDataSource.startTest(request)
            .transform { userTaskMapper.mapUserTask(this) }
    }

    override suspend fun continueTest(testId: Int): CallResult<UserTask> {
        return userTestsDataSource.continueTest(testId)
            .transform { userTaskMapper.mapUserTask(this) }
    }
}