package com.flaringapp.testingsimulator.data.repository.profile_statistics

import com.flaringapp.testingsimulator.data.network.features.profiles_tatistics.response.ProfileStatisticsResponse
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics

interface ProfileStatisticsMapper {

    fun mapProfileStatistics(dto: List<ProfileStatisticsResponse>): List<ProfileStatistics>

    fun mapProfileStatistics(dto: ProfileStatisticsResponse): ProfileStatistics

}

class ProfileStatisticsMapperImpl : ProfileStatisticsMapper {

    override fun mapProfileStatistics(
        dto: List<ProfileStatisticsResponse>
    ): List<ProfileStatistics> {
        return dto.map { mapProfileStatistics(it) }
    }

    override fun mapProfileStatistics(dto: ProfileStatisticsResponse): ProfileStatistics {
        return ProfileStatistics(
            value = dto.value,
            label = dto.label,
            emojiId = dto.emojiId,
        )
    }
}