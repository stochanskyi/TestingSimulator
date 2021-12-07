package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.presentation.auth.login.AdminLoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import org.koin.dsl.module

val PresentationModule = module {

    factory<LoginViewBehaviour> { AdminLoginViewBehaviour() }

}