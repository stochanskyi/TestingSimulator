package com.flaringapp.testingsimulator.presentation.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.flaringapp.testingsimulator.R
import com.flaringapp.testingsimulator.core.presentation.appbar.configuration.*

class MainActivity : AppCompatActivity(),
    AppBarConfigurator, AppBarDisposableHandler {

    private var currentAppBarConfiguration = SimpleAppBarConfigurationChange()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuId = currentAppBarConfiguration.menuId ?: return false
        menuInflater.inflate(menuId, menu)
        currentAppBarConfiguration.menuCreatedListener?.invoke(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val listener = currentAppBarConfiguration.itemSelectedListener ?: return false
        return listener.invoke(item)
    }

    override fun configureAppBar(
        configure: AppBarConfigurationChange.() -> Unit
    ): AppBarConfigurationDisposable {
        val newConfiguration = SimpleAppBarConfigurationChange()
        newConfiguration.configure()

        updateAppBar {
            newConfiguration.apply()
            currentAppBarConfiguration = newConfiguration
        }

        return SimpleAppBarConfigurationDisposable(currentAppBarConfiguration)
    }

    override fun modifyAppBarConfiguration(configure: AppBarConfigurationChange.() -> Unit) {
        updateAppBar {
            currentAppBarConfiguration.apply(configure)
            currentAppBarConfiguration.apply()
        }
    }

    override fun updateMenu() {
        invalidateOptionsMenu()
    }

    override fun handleDispose(configurationChange: AppBarConfigurationChange) {
        if (currentAppBarConfiguration !== configurationChange) return

        val newConfiguration = SimpleAppBarConfigurationChange()
        updateAppBar {
            newConfiguration.apply()
            currentAppBarConfiguration = newConfiguration
        }
    }

    private fun updateAppBar(action: () -> Unit) {
        val lastMenuId = currentAppBarConfiguration.menuId

        action()

        if (currentAppBarConfiguration.menuId != lastMenuId) {
            invalidateOptionsMenu()
        }
    }

    private fun AppBarConfigurationChange.apply() {
        showNavigationButton?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
        }
        title?.let {
            supportActionBar?.title = it
        }
    }
}