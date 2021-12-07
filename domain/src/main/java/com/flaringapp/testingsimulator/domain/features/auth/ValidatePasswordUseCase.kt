package com.flaringapp.testingsimulator.domain.features.auth

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean
}

class ValidatePasswordUseCaseImpl(
    private val validator: Validator,
) : ValidatePasswordUseCase {

    override fun invoke(password: String): Boolean {
        return validator.validatePassword(password)
    }
}