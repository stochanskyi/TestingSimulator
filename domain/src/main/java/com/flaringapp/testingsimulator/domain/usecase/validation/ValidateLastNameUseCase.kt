package com.flaringapp.testingsimulator.domain.usecase.validation

import com.flaringapp.testingsimulator.domain.validation.Validator

interface ValidateLastNameUseCase {
    operator fun invoke(lastName: String): Boolean
}

class ValidateLastNameUseCaseImpl(
    private val validator: Validator,
) : ValidateLastNameUseCase {

    override fun invoke(lastName: String): Boolean {
        return validator.validateLastName(lastName)
    }
}