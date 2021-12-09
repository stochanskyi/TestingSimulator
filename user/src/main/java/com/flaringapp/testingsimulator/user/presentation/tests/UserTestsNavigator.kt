package com.flaringapp.testingsimulator.user.presentation.tests

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.tests.TestsFragmentDirections
import com.flaringapp.testingsimulator.presentation.features.tests.navigation.TestsNavigator

class UserTestsNavigator : TestsNavigator {
    override fun navigateToTest(navController: NavController, testId: Int, testName: String) {
        val action = TestsFragmentDirections.actionFragmentTestsToFragmentTestDetails(testId, testName)
        navController.navigate(action)
    }
}