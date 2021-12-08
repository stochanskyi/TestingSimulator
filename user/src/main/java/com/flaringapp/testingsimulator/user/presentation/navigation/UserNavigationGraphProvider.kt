package com.flaringapp.testingsimulator.user.presentation.navigation

import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider
import com.flaringapp.testingsimulator.user.R

class UserNavigationGraphProvider : NavigationGraphProvider {
    override fun provideGraphId(): Int = R.navigation.main_nav_graph
}