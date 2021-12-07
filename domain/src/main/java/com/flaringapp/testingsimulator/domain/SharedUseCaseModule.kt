package com.flaringapp.testingsimulator.domain

import com.flaringapp.testingsimulator.domain.usecase.validation.*
import org.koin.dsl.module

val SharedUseCaseModule = module {

    factory<ValidateEmailUseCase> { ValidateEmailUseCaseImpl(get()) }

    factory<ValidatePasswordUseCase> { ValidatePasswordUseCaseImpl(get()) }

    factory<ValidateFirstNameUseCase> { ValidateFirstNameUseCaseImpl(get()) }
    factory<ValidateLastNameUseCase> { ValidateLastNameUseCaseImpl(get()) }

}