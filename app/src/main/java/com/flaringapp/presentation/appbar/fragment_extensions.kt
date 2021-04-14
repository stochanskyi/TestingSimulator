package com.flaringapp.presentation.appbar

import androidx.fragment.app.Fragment

fun Fragment.configureAppBar(configuration: AppBarConfigurationChange.() -> Unit) {
    (activity as? AppBarConfigurator)?.configureAppBar(configuration)
}