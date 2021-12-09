package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.domain.login.AdminLoginUseCase
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.EditAdminProfile
import com.flaringapp.testingsimulator.admin.domain.profile.EditAdminProfileUseCase
import com.flaringapp.testingsimulator.admin.domain.profile.GetAdminProfileUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.GetAdminTestsUseCase
import com.flaringapp.testingsimulator.admin.domain.tests.models.AdminTest
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { AdminLoginUseCase(get(), get(), get()) }

    factory<GetProfileUseCase<AdminProfile>> { GetAdminProfileUseCase(get()) }

    factory<EditProfileUseCase<EditAdminProfile, AdminProfile>> { EditAdminProfileUseCase(get()) }

    factory<GetTestsUseCase<AdminTest>> { GetAdminTestsUseCase(get()) }
}