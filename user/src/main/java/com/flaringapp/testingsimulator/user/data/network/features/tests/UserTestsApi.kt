package com.flaringapp.testingsimulator.user.data.network.features.tests

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestDetailsResponse
import com.flaringapp.testingsimulator.user.data.network.features.tests.response.UserTestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserTestsApi {

    @GET("tests")
    suspend fun getTests(@Query("topicId") topicId: Int): ApiResponseList<UserTestResponse>

    @GET("test")
    suspend fun getTestDetails(@Query("testId") testId: Int): ApiResponse<UserTestDetailsResponse>

}