package com.flaringapp.testingsimulator.data.network.features.profiles_statistics

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.response.ProfileStatisticsResponse
import retrofit2.http.GET

interface ProfileStatisticsApi {

    @GET("User/statistic")
    suspend fun getProfileStatistics(): ApiResponseList<ProfileStatisticsResponse>

}