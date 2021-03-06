package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.presentation.features.auth.launcher.ScreenLauncher
import com.flaringapp.testingsimulator.presentation.launcher.ScreenLauncherImpl
import org.koin.dsl.module

val ApplicationPresentationModule = module {

    factory<ScreenLauncher> { ScreenLauncherImpl() }

}