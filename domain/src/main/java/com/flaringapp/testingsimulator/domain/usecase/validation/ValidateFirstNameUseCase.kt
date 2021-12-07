package com.flaringapp.testingsimulator.domain.usecase.validation

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidateFirstNameUseCase {
    operator fun invoke(firstName: String): Boolean
}

class ValidateFirstNameUseCaseImpl(
    private val validator: Validator,
) : ValidateFirstNameUseCase {

    override fun invoke(firstName: String): Boolean {
        return validator.validateFirstName(firstName)
    }
}