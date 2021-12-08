package com.flaringapp.testingsimulator.admin.data.repository.profile

import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.data.network.features.edit_profile.response.EditProfileResponse

interface AdminEditProfileMapper {

    fun map(dto: EditProfileResponse) : AdminProfile

}

class AdminEditProfileMapperImpl : AdminEditProfileMapper {

    override fun map(dto: EditProfileResponse): AdminProfile {
        return AdminProfile(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.lastName,
            workPlace = dto.workplace,
            role = dto.role,
        )
    }
}