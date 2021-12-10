package com.flaringapp.testingsimulator.admin.data.repository.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSource
import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.ChangeTestStateRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.CreateTestRequest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList

interface AdminTestsRepository {

    suspend fun getTests(topicId: Int): CallResultList<AdminTest>

    suspend fun createTest(topicId: Int): CallResult<AdminTest>

    suspend fun getTestDetailed(testId: Int): CallResult<AdminTestDetailed>

    suspend fun changeTestState(testId: Int, state: Int): CallResult<AdminTestDetailed>

}

class AdminTestsRepositoryImpl(
    private val adminTestsDataSource: AdminTestsDataSource,
    private val adminTestMapper: AdminTestMapper,
) : AdminTestsRepository {

    override suspend fun getTests(topicId: Int): CallResultList<AdminTest> {
        return adminTestsDataSource.getTests(topicId)
            .transform { adminTestMapper.mapTests(this) }
    }

    override suspend fun createTest(topicId: Int): CallResult<AdminTest> {
        val request = CreateTestRequest(topicId)

        return adminTestsDataSource.createTest(request)
            .transform { adminTestMapper.mapTest(this) }
    }

    override suspend fun getTestDetailed(testId: Int): CallResult<AdminTestDetailed> {
        return adminTestsDataSource.getTest(testId)
            .transform { adminTestMapper.mapTestWithStatistics(this) }
    }

    override suspend fun changeTestState(
        testId: Int,
        state: Int
    ): CallResult<AdminTestDetailed> {
        val request = ChangeTestStateRequest(testId, state)
        return adminTestsDataSource.changeTestState(request)
            .transform { adminTestMapper.mapTestWithStatistics(this) }
    }
}