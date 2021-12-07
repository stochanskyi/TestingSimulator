package com.flaringapp.testingsimulator.domain.usecase.validation

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidatePasswordEqualityUseCase {
    operator fun invoke(password: String, confirmPassword: String): Boolean
}

class ValidatePasswordEqualityUseCaseImpl(
    private val validator: Validator,
) : ValidatePasswordEqualityUseCase {

    override fun invoke(password: String, confirmPassword: String): Boolean {
        return validator.validatePasswordEquality(password, confirmPassword)
    }
}