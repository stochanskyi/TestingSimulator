package com.flaringapp.testingsimulator.user.data.repository.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.user.data.network.features.tests.UserTestsDataSource
import com.flaringapp.testingsimulator.user.domain.tests.UserTest

interface UserTestsRepository {
    suspend fun getTests(topicId: Int): CallResultList<UserTest>
}

class UserTestsRepositoryImpl(
    private val userTestsDataSource: UserTestsDataSource,
    private val userTestMapper: UserTestMapper
) : UserTestsRepository {

    override suspend fun getTests(topicId: Int): CallResultList<UserTest> {
        val result = userTestsDataSource.getTests(topicId)

        return result.transform { userTestMapper.mapTests(this) }
    }
}