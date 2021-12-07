package com.flaringapp.testingsimulator.admin.data.repository.auth.mappers

import com.flaringapp.testingsimulator.admin.data.network.features.auth.models.response.AdminLoginResponse
import com.flaringapp.testingsimulator.admin.data.network.features.common.AdminDataModel
import com.flaringapp.testingsimulator.admin.data.repository.auth.models.AdminInfo
import com.flaringapp.testingsimulator.admin.data.repository.auth.models.AdminLoginInfo

interface AdminLoginResponseMapper {
    fun map(loginResponse: AdminLoginResponse): AdminLoginInfo
}

class AdminLoginResponseMapperImpl : AdminLoginResponseMapper {

    override fun map(loginResponse: AdminLoginResponse): AdminLoginInfo {
        return AdminLoginInfo(
            token = loginResponse.token,
            mapUserInfo(loginResponse.user),
        )
    }

    private fun mapUserInfo(dto: AdminDataModel): AdminInfo {
        return AdminInfo(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            workplace = dto.workplace ?: "",
            role = dto.role ?: "",
        )
    }

}