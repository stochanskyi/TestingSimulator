package com.flaringapp.testingsimulator.admin.data.network.features.tests

import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.ChangeTestStateRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tests.request.CreateTestRequest
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestWithStatisticsModel
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.core.data.network.base.validateList

interface AdminTestsDataSource {

    suspend fun getTests(topicId: Int): CallResultList<AdminTestResponse>

    suspend fun getTest(testId: Int): CallResult<AdminTestWithStatisticsModel>

    suspend fun createTest(request: CreateTestRequest): CallResult<AdminTestResponse>

    suspend fun changeTestState(
        request: ChangeTestStateRequest
    ): CallResult<AdminTestWithStatisticsModel>

}

class AdminTestsDataSourceImpl(
    private val testsApi: AdminTestsApi
) : AdminTestsDataSource {

    override suspend fun getTests(topicId: Int): CallResultList<AdminTestResponse> {
        return testsApi.getTests(topicId).validateList()
    }

    override suspend fun getTest(testId: Int): CallResult<AdminTestWithStatisticsModel> {
        return testsApi.getTest(testId).validate()
    }

    override suspend fun createTest(request: CreateTestRequest): CallResult<AdminTestResponse> {
        return testsApi.createTest(request).validate()
    }

    override suspend fun changeTestState(
        request: ChangeTestStateRequest
    ): CallResult<AdminTestWithStatisticsModel> {
        return testsApi.changeTestState(request).validate()
    }
}