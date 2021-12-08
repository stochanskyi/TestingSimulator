package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.domain.login.AdminLoginUseCase
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.GetAdminProfileUseCase
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { AdminLoginUseCase(get(), get(), get()) }

    factory<GetProfileUseCase<AdminProfile>> { GetAdminProfileUseCase(get()) }

}