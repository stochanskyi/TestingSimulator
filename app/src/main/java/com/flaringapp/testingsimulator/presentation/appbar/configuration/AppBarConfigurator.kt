package com.flaringapp.testingsimulator.presentation.appbar.configuration

interface AppBarConfigurator {

    fun configureAppBar(
        configure: AppBarConfigurationChange.() -> Unit
    ): AppBarConfigurationDisposable

    fun modifyAppBarConfiguration(
        configure: AppBarConfigurationChange.() -> Unit
    )

    fun updateMenu()

}