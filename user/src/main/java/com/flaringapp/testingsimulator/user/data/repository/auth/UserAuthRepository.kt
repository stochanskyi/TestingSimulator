package com.flaringapp.testingsimulator.user.data.repository.auth

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.user.data.network.features.auth.AuthDataSource
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.LoginRequest
import com.flaringapp.testingsimulator.user.data.network.features.auth.models.request.SignUpRequest
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapper
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
    private val authDataSource: AuthDataSource,
    private val loginResponseMapper: LoginResponseMapper
) : UserAuthRepository {

    override suspend fun login(
        email: String,
        password: String,
    ): CallResult<UserLoginInfo> {
        val loginRequest = LoginRequest(email, password)

        return authDataSource.login(loginRequest).transform {
            loginResponseMapper.map(this)
        }
    }

    override suspend fun signUp(registrationData: UserRegistrationData): CallResult<UserLoginInfo> {
        val signUpRequest = SignUpRequest(
            email = registrationData.email,
            firstName = registrationData.firstName,
            lastName = registrationData.lastName,
            studying = registrationData.studyingAt,
            workplace = registrationData.workPlace,
            role = registrationData.role,
            password = registrationData.password,
            passwordRepeat = registrationData.repeatPassword,
        )

        return authDataSource.signUp(signUpRequest).transform {
            loginResponseMapper.map(this)
        }
    }

}