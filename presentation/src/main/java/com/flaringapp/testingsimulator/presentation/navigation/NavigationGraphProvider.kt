package com.flaringapp.testingsimulator.presentation.navigation

import androidx.annotation.NavigationRes

interface NavigationGraphProvider {

    @NavigationRes
    fun provideGraphId(): Int

}