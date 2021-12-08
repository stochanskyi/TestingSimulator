package com.flaringapp.testingsimulator.domain.features.profile_statistics

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics

interface ProfileStatisticsRepository {

    suspend fun getProfileStatistics(): CallResultList<ProfileStatistics>

}