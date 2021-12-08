package com.flaringapp.testingsimulator.user.data.repository.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.network.features.auth.UserAuthDataSource
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserLoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.request.UserSignUpRequest
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.UserLoginMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.models.UserLoginInfo
import com.flaringapp.testingsimulator.user.domain.signup.models.UserRegistrationData

interface UserAuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): CallResult<UserLoginInfo>

    suspend fun signUp(
        registrationData: UserRegistrationData
    ): CallResult<UserLoginInfo>

}

class UserAuthRepositoryImpl(
    private val authDataSource: UserAuthDataSource,
    private val loginMapper: UserLoginMapper
) : UserAuthRepository {

    override suspend fun login(
        email: String,
        password: String,
    ): CallResult<UserLoginInfo> {
        val loginRequest = UserLoginRequest(email, password)

        return authDataSource.login(loginRequest)
            .transform {
                loginMapper.map(this)
            }
    }

    override suspend fun signUp(registrationData: UserRegistrationData): CallResult<UserLoginInfo> {
        val signUpRequest = UserSignUpRequest(
            email = registrationData.email,
            firstName = registrationData.firstName,
            lastName = registrationData.lastName,
            studying = registrationData.studyingAt,
            workPlace = registrationData.workPlace,
            role = registrationData.role,
            password = registrationData.password,
            passwordRepeat = registrationData.repeatPassword,
        )

        return authDataSource.signUp(signUpRequest)
            .transform {
                loginMapper.map(this)
            }
    }
}