package com.flaringapp.testingsimulator.admin.presentation.topics

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData
import com.flaringapp.testingsimulator.presentation.features.topics.TopicsFragmentDirections
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator

class AdminTopicsNavigator : TopicsNavigator {

    override fun navigateToTests(navController: NavController, topicPreliminaryData: TopicPreliminaryData) {
        val action = TopicsFragmentDirections.actionFragmentTopicsToFragmentTests(
            topicPreliminaryData
        )
        navController.navigate(action)
    }

    override fun navigateToProfile(navController: NavController) {
        val action = TopicsFragmentDirections.actionFragmentTopicsToFragmentProfile()
        navController.navigate(action)
    }

}