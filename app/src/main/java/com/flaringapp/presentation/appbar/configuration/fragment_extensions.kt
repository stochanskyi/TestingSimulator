package com.flaringapp.presentation.appbar.configuration

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun Fragment.configureAppBarWithLifecycle(
    configure: AppBarConfigurationChange.() -> Unit
) {
    val disposable = configureAppBar(configure)
    disposable.disposeOnLifecycle(viewLifecycleOwner.lifecycle) {
        resolve(requireActivity())
    }
}

inline fun AppBarConfigurationDisposable.disposeOnLifecycle(
    lifecycle: Lifecycle,
    crossinline provideDisposeHandler: () -> AppBarDisposableHandler
) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            lifecycle.removeObserver(this)
            dispose(provideDisposeHandler())
        }
    })
}

fun Fragment.configureAppBar(
    configure: AppBarConfigurationChange.() -> Unit
): AppBarConfigurationDisposable {
    return resolveAppBarConfigurator().configureAppBar(configure)
}

fun Fragment.updateAppBarConfiguration(
    configure: AppBarConfigurationChange.() -> Unit
) {
    resolveAppBarConfigurator().modifyAppBarConfiguration(configure)
}

fun Fragment.updateMenu() {
    resolveAppBarConfigurator().updateMenu()
}

private fun Fragment.resolveAppBarConfigurator(): AppBarConfigurator = resolve(requireActivity())

private inline fun <reified T> resolve(target: Any): T {
    return target as? T ?: throw generateTargetCastException<T>(target)
}

private inline fun <reified T> generateTargetCastException(
    target: Any
) = IllegalStateException(
    "${target::class.qualifiedName} Should implement ${T::class.qualifiedName}"
)