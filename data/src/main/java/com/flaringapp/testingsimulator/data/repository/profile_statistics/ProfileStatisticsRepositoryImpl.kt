package com.flaringapp.testingsimulator.data.repository.profile_statistics

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.data.network.features.profiles_statistics.ProfileStatisticsDataSource
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics
import com.flaringapp.testingsimulator.domain.features.profile_statistics.ProfileStatisticsRepository

class ProfileStatisticsRepositoryImpl(
    private val dataSource: ProfileStatisticsDataSource,
    private val mapper: ProfileStatisticsMapper,
) : ProfileStatisticsRepository {

    override suspend fun getProfileStatistics(): CallResultList<ProfileStatistics> {
        return dataSource.getProfileStatistics()
            .transform { mapper.mapProfileStatistics(this) }
    }
}