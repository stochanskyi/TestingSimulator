package com.flaringapp.testingsimulator.admin.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import com.flaringapp.testingsimulator.admin.data.network.features.tests.response.AdminTestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AdminTestsApi {

    @GET("tests")
    suspend fun getTests(@Query("topicId") topicId: Int): ApiResponseList<AdminTestResponse>

}