package com.flaringapp.presentation.appbar.menu

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

typealias AppBarMenuContracts = List<AppBarMenuContract>

fun AppBarMenuConsumer.consumeMenuItemSelected(item: MenuItem): Boolean {
    val contract = appBarMenuContracts.find {
        it.itemId == item.itemId
    } ?: return false

    contract.handleAction(item)
    return true
}

fun AppBarMenuLazyConsumer.appBarMenuContracts(): Lazy<AppBarMenuContracts> {
    return lazyOf(provideAppBarMenuContracts())
}

fun <T> T.fragmentAppBarMenuContracts(): Lazy<AppBarMenuContracts>
    where T : Fragment, T : AppBarMenuLazyConsumer {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            setHasOptionsMenu(true)
        }
    })

    return lazyOf(provideAppBarMenuContracts())
}