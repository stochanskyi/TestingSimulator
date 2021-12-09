package com.flaringapp.testingsimulator.admin.presentation.navigation

import com.flaringapp.testingsimulator.admin.R
import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider

class AdminNavigationGraphProvider : NavigationGraphProvider {
    override fun provideGraphId(): Int = R.navigation.admin_nav_graph
}