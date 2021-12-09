package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.edit_profile.EditProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator
import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider
import com.flaringapp.testingsimulator.user.presentation.auth.login.UserLoginViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.auth.signup.UserSignUpViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.edit_profile.UserEditProfileBehaviour
import com.flaringapp.testingsimulator.user.presentation.navigation.UserNavigationGraphProvider
import com.flaringapp.testingsimulator.user.presentation.profile.UserProfileBehaviour
import com.flaringapp.testingsimulator.user.presentation.topics.UserTopicsNavigator
import org.koin.dsl.module

val PresentationModule = module {

    factory<NavigationGraphProvider> { UserNavigationGraphProvider() }

    factory<TopicsNavigator> { UserTopicsNavigator() }

    factory<LoginViewBehaviour> { UserLoginViewBehaviour() }

    factory<SignUpViewBehaviour> { UserSignUpViewBehaviour(get()) }

    factory<ProfileBehaviour> { UserProfileBehaviour(get(), get()) }

    factory<EditProfileBehaviour> { UserEditProfileBehaviour(get()) }

}