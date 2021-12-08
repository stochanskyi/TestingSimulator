package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.auth.login.UserLoginViewBehaviour
import com.flaringapp.testingsimulator.user.presentation.auth.signup.UserSignUpViewBehaviour
import org.koin.dsl.module

val PresentationModule = module {

    factory<LoginViewBehaviour> { UserLoginViewBehaviour() }

    factory<SignUpViewBehaviour> { UserSignUpViewBehaviour(get()) }

}