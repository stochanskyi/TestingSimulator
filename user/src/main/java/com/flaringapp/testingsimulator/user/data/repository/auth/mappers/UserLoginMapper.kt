package com.flaringapp.testingsimulator.user.data.repository.auth.mappers

import com.flaringapp.testingsimulator.user.data.network.features.auth.response.UserProfileWithTokenResponse
import com.flaringapp.testingsimulator.user.data.network.features.auth.response.UserLoginProfileResponse
import com.flaringapp.testingsimulator.user.data.repository.auth.models.UserLoginInfo
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

interface UserLoginMapper {

    fun map(dto: UserProfileWithTokenResponse): UserLoginInfo

}

class UserLoginMapperImpl : UserLoginMapper {

    override fun map(dto: UserProfileWithTokenResponse): UserLoginInfo {
        return UserLoginInfo(
            token = dto.token,
            mapProfile(dto.user),
        )
    }

    private fun mapProfile(dto: UserLoginProfileResponse): UserProfile {
        return UserProfile(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            studying = dto.studying ?: "",
            workPlace = dto.workPlace ?: "",
            role = dto.role ?: "",
        )
    }
}