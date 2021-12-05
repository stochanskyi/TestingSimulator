package com.flaringapp.presentation.appbar.configuration

class SimpleAppBarConfigurationDisposable(
    private val configurationChange: AppBarConfigurationChange
) : AppBarConfigurationDisposable {

    override fun dispose(handler: AppBarDisposableHandler) {
        handler.handleDispose(configurationChange)
    }
}