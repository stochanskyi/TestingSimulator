package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.domain.features.auth.GetLastEmailUseCase
import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.features.profile.EditProfileUseCase
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.domain.features.profile.LogoutUseCase
import com.flaringapp.testingsimulator.domain.features.tests.GetTestsUseCase
import com.flaringapp.testingsimulator.user.domain.login.UserIsLoggedInUseCase
import com.flaringapp.testingsimulator.user.domain.login.UserLoginUseCase
import com.flaringapp.testingsimulator.user.domain.profile.*
import com.flaringapp.testingsimulator.user.domain.signup.UserSignUpUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.AnswerUserTaskUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.GetTaskOrContinueTestUseCase
import com.flaringapp.testingsimulator.user.domain.tasks.GetUserTaskUseCase
import com.flaringapp.testingsimulator.user.domain.tests.ContinueTestUseCase
import com.flaringapp.testingsimulator.user.domain.tests.GetUserTestDetailsUseCase
import com.flaringapp.testingsimulator.user.domain.tests.GetUserTestsUseCase
import com.flaringapp.testingsimulator.user.domain.tests.StartTestUseCase
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest
import org.koin.dsl.module

val UseCaseModule = module {

    factory<IsLoggedInUseCase> { UserIsLoggedInUseCase(get()) }
    factory<LoginUseCase> { UserLoginUseCase(get(), get(), get()) }

    factory { UserSignUpUseCase(get(), get(), get()) }

    factory<GetProfileUseCase<UserProfile>> { GetUserProfileUseCase(get()) }
    factory<EditProfileUseCase<EditUserProfile, UserProfile>> { EditUserProfileUseCase(get()) }

    factory<GetLastEmailUseCase> { GetUserEmailUseCase(get()) }

    factory<LogoutUseCase> { UserLogoutUseCase(get(), get()) }

    factory<GetTestsUseCase<UserTest>> { GetUserTestsUseCase(get()) }

    factory { GetUserTestDetailsUseCase(get()) }

    factory { StartTestUseCase(get(), get()) }
    factory { ContinueTestUseCase(get(), get()) }

    factory { GetUserTaskUseCase(get()) }
    factory { GetTaskOrContinueTestUseCase(get(), get()) }
    factory { AnswerUserTaskUseCase(get()) }

}