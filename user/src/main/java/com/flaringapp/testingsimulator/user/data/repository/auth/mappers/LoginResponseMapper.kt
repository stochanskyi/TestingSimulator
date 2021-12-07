package com.flaringapp.testingsimulator.user.data.repository.auth.mappers

import com.flaringapp.testingsimulator.user.data.network.features.auth.models.response.LoginResponse
import com.flaringapp.testingsimulator.user.data.network.features.common.UserDataModel
import com.flaringapp.testingsimulator.user.data.repository.auth.models.UserInfo
import com.flaringapp.testingsimulator.user.data.repository.auth.models.UserLoginInfo

interface LoginResponseMapper {
    fun map(loginResponse: LoginResponse): UserLoginInfo
}

class LoginResponseMapperImpl : LoginResponseMapper {

    override fun map(loginResponse: LoginResponse): UserLoginInfo {
        return UserLoginInfo(
            token = loginResponse.token,
            mapUserInfo(loginResponse.user),
        )
    }

    private fun mapUserInfo(dto: UserDataModel): UserInfo {
        return UserInfo(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            email = dto.email,
            studying = dto.studying ?: "",
            workplace = dto.workplace ?: "",
            role = dto.role ?: "",
        )
    }

}