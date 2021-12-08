package com.flaringapp.testingsimulator.domain

import com.flaringapp.testingsimulator.domain.features.profile_statistics.GetProfileStatisticsUseCase
import com.flaringapp.testingsimulator.domain.features.profile_statistics.GetProfileStatisticsUseCaseImpl
import com.flaringapp.testingsimulator.domain.usecase.validation.*
import org.koin.dsl.module

val UseCaseModule = module {

    factory<ValidateEmailUseCase> { ValidateEmailUseCaseImpl(get()) }

    factory<ValidatePasswordUseCase> { ValidatePasswordUseCaseImpl(get()) }

    factory<ValidateFirstNameUseCase> { ValidateFirstNameUseCaseImpl(get()) }
    factory<ValidateLastNameUseCase> { ValidateLastNameUseCaseImpl(get()) }

    factory<ValidatePasswordEqualityUseCase> { ValidatePasswordEqualityUseCaseImpl(get()) }

    factory<GetProfileStatisticsUseCase> { GetProfileStatisticsUseCaseImpl(get()) }

}