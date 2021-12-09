package com.flaringapp.testingsimulator.admin.data.repository.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSource
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTestDetailed
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList

interface AdminTestsRepository {

    suspend fun getTests(topicId: Int): CallResultList<AdminTest>

    suspend fun getTestDetailed(testId: Int): CallResult<AdminTestDetailed>

}

class AdminTestsRepositoryImpl(
    private val adminTestsDataSource: AdminTestsDataSource,
    private val adminTestMapper: AdminTestMapper,
) : AdminTestsRepository {

    override suspend fun getTests(topicId: Int): CallResultList<AdminTest> {
        return adminTestsDataSource.getTests(topicId)
            .transform { adminTestMapper.mapTests(this) }
    }

    override suspend fun getTestDetailed(testId: Int): CallResult<AdminTestDetailed> {
        return adminTestsDataSource.getTest(testId)
            .transform { adminTestMapper.mapTestWithStatistics(this) }
    }
}