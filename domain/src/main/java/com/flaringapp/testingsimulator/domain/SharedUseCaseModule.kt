package com.flaringapp.testingsimulator.domain

import com.flaringapp.testingsimulator.domain.features.auth.ValidateEmailUseCase
import com.flaringapp.testingsimulator.domain.features.auth.ValidateEmailUseCaseImpl
import com.flaringapp.testingsimulator.domain.features.auth.ValidatePasswordUseCase
import com.flaringapp.testingsimulator.domain.features.auth.ValidatePasswordUseCaseImpl
import org.koin.dsl.module

val SharedUseCaseModule = module {

    factory<ValidateEmailUseCase> { ValidateEmailUseCaseImpl(get()) }

    factory<ValidatePasswordUseCase> { ValidatePasswordUseCaseImpl(get()) }

}