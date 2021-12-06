package com.flaringapp.app.di

import com.flaringapp.data.usecase.auth.LoginUseCase
import com.flaringapp.data.usecase.auth.UserLoginUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { UserLoginUseCase() }

}