package com.flaringapp.testingsimulator.data.network.features.profiles_tatistics

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponseList
import com.flaringapp.testingsimulator.data.network.features.profiles_tatistics.response.ProfileStatisticsResponse

interface ProfileStatisticsApi {

    suspend fun getProfileStatistics(): ApiResponseList<ProfileStatisticsResponse>

}