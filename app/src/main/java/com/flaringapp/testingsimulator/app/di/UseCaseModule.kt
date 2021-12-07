package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.data.usecase.auth.LoginUseCase
import com.flaringapp.testingsimulator.data.usecase.auth.UserLoginUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { UserLoginUseCase() }

}