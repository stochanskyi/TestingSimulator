package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.user.domain.login.UserLoginUseCase
import com.flaringapp.testingsimulator.user.domain.signup.UserSignUpUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { UserLoginUseCase(get(), get()) }

    factory { UserSignUpUseCase(get(), get()) }
}