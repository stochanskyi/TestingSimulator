package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.domain.login.AdminLoginUseCase
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { AdminLoginUseCase(get(), get()) }

}