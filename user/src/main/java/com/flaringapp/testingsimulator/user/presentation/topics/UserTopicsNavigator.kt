package com.flaringapp.testingsimulator.user.presentation.topics

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.topics.TopicsFragmentDirections
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator

class UserTopicsNavigator : TopicsNavigator {
    override fun navigateToTasks(navController: NavController) {
        //TODO
    }

    override fun navigateToProfile(navController: NavController) {
        val action = TopicsFragmentDirections.actionFragmentTopicsToFragmentProfile()
        navController.navigate(action)
    }
}