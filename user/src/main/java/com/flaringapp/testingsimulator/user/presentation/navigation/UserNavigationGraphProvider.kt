package com.flaringapp.testingsimulator.user.presentation.navigation

import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider
import com.flaringapp.testingsimulator.user.R

class UserNavigationGraphProvider : NavigationGraphProvider {
    override fun provideGraphId(): Int = R.navigation.user_nav_graph
}