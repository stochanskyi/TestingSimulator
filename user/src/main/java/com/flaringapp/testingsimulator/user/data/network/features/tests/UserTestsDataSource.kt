package com.flaringapp.testingsimulator.user.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.core.data.network.base.validateList
import com.flaringapp.testingsimulator.user.data.network.features.tasks.request.StartTestRequest
import com.flaringapp.testingsimulator.user.data.network.features.tasks.response.UserTaskResponse
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestDetailsResponse
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse

interface UserTestsDataSource {
    suspend fun startTest(request: StartTestRequest): CallResult<UserTaskResponse>
    suspend fun getTests(topicId: Int): CallResultList<UserTestResponse>
    suspend fun getTestDetails(testId: Int): CallResult<UserTestDetailsResponse>
}

class UserTestsDataSourceImpl(
    private val testsApi: UserTestsApi
) : UserTestsDataSource {

    override suspend fun startTest(request: StartTestRequest): CallResult<UserTaskResponse> {
        return testsApi.startTest(request).validate()
    }

    override suspend fun getTests(topicId: Int): CallResultList<UserTestResponse> {
        return testsApi.getTests(topicId).validateList()
    }

    override suspend fun getTestDetails(testId: Int): CallResult<UserTestDetailsResponse> {
        return testsApi.getTestDetails(testId).validate()
    }

}