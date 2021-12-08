package com.flaringapp.testingsimulator.presentation.features.topics.navigation

import androidx.navigation.NavController

interface TopicsNavigator {
    fun navigateToTasks(navController: NavController)
    fun navigateToProfile(navController: NavController)
}