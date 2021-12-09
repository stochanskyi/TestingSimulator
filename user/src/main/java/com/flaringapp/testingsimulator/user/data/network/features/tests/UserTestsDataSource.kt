package com.flaringapp.testingsimulator.user.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.network.base.validateList
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse

interface UserTestsDataSource {
    suspend fun getTests(topicId: Int): CallResultList<UserTestResponse>
}

class UserTestsDataSourceImpl(
    private val testsApi: UserTestsApi
) : UserTestsDataSource {

    override suspend fun getTests(topicId: Int): CallResultList<UserTestResponse> {
        return testsApi.getTests(topicId).validateList()
    }

}