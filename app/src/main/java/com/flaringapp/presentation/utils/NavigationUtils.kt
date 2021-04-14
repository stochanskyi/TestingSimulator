package com.flaringapp.presentation.utils

import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation

object NavigationUtils {

    fun attachNavGraph(fragment: FragmentContainerView, @NavigationRes navigationRes: Int) {
        val controller = Navigation.findNavController(fragment)
        controller.graph = controller.navInflater.inflate(navigationRes)
    }

}