package com.flaringapp.testingsimulator.presentation.features.tests.navigation

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData

interface TestsNavigator {
    fun navigateToTest(navController: NavController, testId: Int, testName: String)
}