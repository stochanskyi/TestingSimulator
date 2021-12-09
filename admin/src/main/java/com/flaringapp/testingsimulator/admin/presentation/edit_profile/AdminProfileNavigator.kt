package com.flaringapp.testingsimulator.admin.presentation.edit_profile

import androidx.navigation.NavController
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileFragmentDirections
import com.flaringapp.testingsimulator.presentation.features.profile.navigation.ProfileNavigator

class AdminProfileNavigator : ProfileNavigator {

    override fun navigateToEditProfile(navController: NavController) {
        val action = ProfileFragmentDirections.actionFragmentProfileToFragmentEditProfile()
        navController.navigate(action)
    }
}