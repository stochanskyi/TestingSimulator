package com.flaringapp.testingsimulator.data.network.features.profiles_statistics

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.core.data.network.base.validate
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.response.ProfileStatisticsResponse

interface ProfileStatisticsDataSource {

    suspend fun getProfileStatistics(): CallResultList<ProfileStatisticsResponse>

}

class ProfileStatisticsDataSourceImpl(
    private val api: ProfileStatisticsApi
) : ProfileStatisticsDataSource {

    override suspend fun getProfileStatistics(): CallResultList<ProfileStatisticsResponse> {
        return api.getProfileStatistics().validate()
    }
}