package com.flaringapp.testingsimulator.admin.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.network.base.validateList
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse

interface AdminTestsDataSource {
    suspend fun getTests(topicId: Int): CallResultList<AdminTestResponse>
}

class AdminTestsDataSourceImpl(
    private val testsApi: AdminTestsApi
) : AdminTestsDataSource {

    override suspend fun getTests(topicId: Int): CallResultList<AdminTestResponse> {
        return testsApi.getTests(topicId).validateList()
    }

}