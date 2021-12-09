package com.flaringapp.testingsimulator.user.presentation.auth.signup

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpViewBehaviour
import com.flaringapp.testingsimulator.user.domain.signup.UserSignUpUseCase
import com.flaringapp.testingsimulator.user.domain.signup.models.UserRegistrationData

class UserSignUpViewBehaviour(
    private val userSignUpUseCase: UserSignUpUseCase
) : SignUpViewBehaviour {

    override suspend fun createAccount(
        email: String,
        firstName: String,
        lastName: String,
        studying: String,
        workPlace: String,
        role: String,
        password: String,
        repeatPassword: String,
    ): CallResultNothing {

        val registrationData = UserRegistrationData(
            email = email,
            firstName = firstName,
            lastName = lastName,
            studying = studying,
            workPlace = workPlace,
            role = role,
            password = password,
            repeatPassword = repeatPassword
        )

        return userSignUpUseCase.signUp(registrationData)
    }

}