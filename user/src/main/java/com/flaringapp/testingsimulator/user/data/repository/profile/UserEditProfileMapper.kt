package com.flaringapp.testingsimulator.user.data.repository.profile

import com.flaringapp.testingsimulator.data.network.features.edit_profile.response.EditProfileResponse
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

interface UserEditProfileMapper {

    fun map(dto: EditProfileResponse) : UserProfile

}

class UserEditProfileMapperImpl : UserEditProfileMapper {

    override fun map(dto: EditProfileResponse): UserProfile {
        return UserProfile(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            studying = dto.studying,
            workPlace = dto.workplace,
            role = dto.role,
        )
    }
}