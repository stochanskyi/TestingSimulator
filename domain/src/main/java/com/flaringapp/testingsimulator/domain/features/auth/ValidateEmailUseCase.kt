package com.flaringapp.testingsimulator.domain.features.auth

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean
}

class ValidateEmailUseCaseImpl(
    private val validator: Validator,
) : ValidateEmailUseCase {

    override fun invoke(email: String): Boolean {
        return validator.validateEmail(email)
    }
}