package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.user.domain.login.UserLoginUseCase
import com.flaringapp.testingsimulator.user.domain.profile.EditUserProfile
import com.flaringapp.testingsimulator.user.domain.profile.EditUserProfileUseCase
import com.flaringapp.testingsimulator.user.domain.signup.UserSignUpUseCase
import com.flaringapp.testingsimulator.user.domain.profile.GetUserProfileUseCase
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile
import org.koin.dsl.module

val UseCaseModule = module {

    factory<LoginUseCase> { UserLoginUseCase(get(), get(), get()) }

    factory { UserSignUpUseCase(get(), get(), get()) }

    factory<GetProfileUseCase<UserProfile>> { GetUserProfileUseCase(get()) }
    factory<EditProfileUseCase<EditUserProfile, UserProfile>> { EditUserProfileUseCase(get()) }

}