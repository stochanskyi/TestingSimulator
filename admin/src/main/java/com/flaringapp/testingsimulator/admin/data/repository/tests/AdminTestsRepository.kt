package com.flaringapp.testingsimulator.admin.data.repository.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.AdminTestsDataSource
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList

interface AdminTestsRepository {
    suspend fun getTests(topicId: Int): CallResultList<AdminTest>
}

class AdminTestsRepositoryImpl(
    private val adminTestsDataSource: AdminTestsDataSource,
    private val adminTestMapper: AdminTestMapper
) : AdminTestsRepository {

    override suspend fun getTests(topicId: Int): CallResultList<AdminTest> {
        val result = adminTestsDataSource.getTests(topicId)

        return result.transform { adminTestMapper.mapTests(this) }
    }
}