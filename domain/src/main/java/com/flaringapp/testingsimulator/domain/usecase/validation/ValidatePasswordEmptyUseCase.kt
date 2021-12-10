package com.flaringapp.testingsimulator.domain.usecase.validation

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidatePasswordEmptyUseCase {
    operator fun invoke(password: String): Boolean
}

class ValidatePasswordEmptyUseCaseImpl(
    private val validator: Validator,
) : ValidatePasswordEmptyUseCase {

    override fun invoke(password: String): Boolean {
        return validator.validatePasswordEmpty(password)
    }
}