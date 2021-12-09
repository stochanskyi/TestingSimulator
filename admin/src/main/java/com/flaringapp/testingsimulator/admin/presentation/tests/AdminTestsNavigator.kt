package com.flaringapp.testingsimulator.admin.presentation.tests

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.tests.TestsFragmentDirections
import com.flaringapp.testingsimulator.presentation.features.tests.navigation.TestsNavigator

class AdminTestsNavigator : TestsNavigator {
    override fun navigateToTest(navController: NavController, testId: Int, testName: String) {
        val action = TestsFragmentDirections.actionFragmentTestsToFragmentAdminTest(testId, testName)
        navController.navigate(action)
    }
}