package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour.EditProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.navigation.ProfileNavigator
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TestsFragmentArgsHandler
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.navigation.TestsNavigator
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator
import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider
import com.flaringapp.testingsimulator.user.presentation.auth.login.UserLoginViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.auth.signup.UserSignUpViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.edit_profile.UserEditProfileBehaviour
import com.flaringapp.testingsimulator.user.presentation.edit_profile.UserProfileNavigator
import com.flaringapp.testingsimulator.user.presentation.navigation.UserNavigationGraphProvider
import com.flaringapp.testingsimulator.user.presentation.navigation.tests.UserTestsFragmentArgsHandler
import com.flaringapp.testingsimulator.user.presentation.profile.UserProfileBehaviour
import com.flaringapp.testingsimulator.user.presentation.task.UserTaskPassingViewModel
import com.flaringapp.testingsimulator.user.presentation.task.UserTaskPassingViewModelImpl
import com.flaringapp.testingsimulator.user.presentation.tests.UserTestsBehaviour
import com.flaringapp.testingsimulator.user.presentation.tests.UserTestsNavigator
import com.flaringapp.testingsimulator.user.presentation.tests.testDetails.UserTestDetailsViewModel
import com.flaringapp.testingsimulator.user.presentation.tests.testDetails.UserTestDetailsViewModelImpl
import com.flaringapp.testingsimulator.user.presentation.topics.UserTopicsNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    factory<NavigationGraphProvider> { UserNavigationGraphProvider() }

    factory<TopicsNavigator> { UserTopicsNavigator() }

    factory<LoginViewBehaviour> { UserLoginViewBehaviour() }

    factory<SignUpViewBehaviour> { UserSignUpViewBehaviour(get()) }

    factory<ProfileNavigator> { UserProfileNavigator() }
    factory<ProfileBehaviour> { UserProfileBehaviour(get(), get()) }

    factory<EditProfileBehaviour> { UserEditProfileBehaviour(get(), get()) }

    factory<TestsNavigator> { UserTestsNavigator() }
    factory<TestsFragmentArgsHandler> { UserTestsFragmentArgsHandler() }
    factory<TestsBehaviour> { UserTestsBehaviour(get(), get(), get()) }

    viewModel<UserTestDetailsViewModel> { UserTestDetailsViewModelImpl(get(), get(), get(), get()) }

    viewModel<UserTaskPassingViewModel> { UserTaskPassingViewModelImpl() }

}