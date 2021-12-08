package com.flaringapp.testingsimulator.domain.features.profile_statistics

import com.flaringapp.testingsimulator.core.data.common.call.CallResultList
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics

interface GetProfileStatisticsUseCase {
    suspend operator fun invoke(): CallResultList<ProfileStatistics>
}

class GetProfileStatisticsUseCaseImpl(
    private val repository: ProfileStatisticsRepository
) : GetProfileStatisticsUseCase {

    override suspend fun invoke(): CallResultList<ProfileStatistics> {
        return repository.getProfileStatistics()
    }
}