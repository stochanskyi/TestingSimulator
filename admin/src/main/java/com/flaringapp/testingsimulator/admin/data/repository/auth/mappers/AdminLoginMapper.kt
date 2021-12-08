package com.flaringapp.testingsimulator.admin.data.repository.auth.mappers

import com.flaringapp.testingsimulator.admin.data.network.features.auth.response.AdminLoginResponse
import com.flaringapp.testingsimulator.admin.data.network.features.auth.response.AdminLoginProfileResponse
import com.flaringapp.testingsimulator.admin.data.repository.auth.models.AdminLoginInfo
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile

interface AdminLoginMapper {

    fun map(dto: AdminLoginResponse): AdminLoginInfo

}

class AdminLoginMapperImpl : AdminLoginMapper {

    override fun map(dto: AdminLoginResponse): AdminLoginInfo {
        return AdminLoginInfo(
            token = dto.token,
            mapProfile(dto.user),
        )
    }

    private fun mapProfile(dto: AdminLoginProfileResponse): AdminProfile {
        return AdminProfile(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            workPlace = dto.workPlace ?: "",
            role = dto.role ?: "",
        )
    }
}