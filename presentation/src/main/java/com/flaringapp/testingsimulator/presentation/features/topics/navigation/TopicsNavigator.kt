package com.flaringapp.testingsimulator.presentation.features.topics.navigation

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData

interface TopicsNavigator {
    fun navigateToTests(navController: NavController, topicPreliminaryData: TopicPreliminaryData)
    fun navigateToProfile(navController: NavController)
}