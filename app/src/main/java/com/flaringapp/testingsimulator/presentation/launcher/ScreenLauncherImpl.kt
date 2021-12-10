package com.flaringapp.testingsimulator.presentation.launcher

import android.content.Context
import android.content.Intent
import com.flaringapp.testingsimulator.presentation.features.auth.AuthActivity
import com.flaringapp.testingsimulator.presentation.features.auth.launcher.ScreenLauncher
import com.flaringapp.testingsimulator.presentation.features.main.MainActivity

class ScreenLauncherImpl: ScreenLauncher {

    override fun launchMainScreen(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    override fun launchLoginScreen(context: Context) {
        context.startActivity(Intent(context, AuthActivity::class.java))
    }
}