package com.flaringapp.presentation.appbar

interface AppBarConfigurator {

    fun configureAppBar(configure: AppBarConfigurationChange.() -> Unit)

}