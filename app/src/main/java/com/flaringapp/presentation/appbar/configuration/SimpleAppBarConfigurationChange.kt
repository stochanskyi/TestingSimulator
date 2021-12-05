package com.flaringapp.presentation.appbar.configuration

import android.view.Menu
import android.view.MenuItem

class SimpleAppBarConfigurationChange(
    override var showNavigationButton: Boolean? = null,
    override var title: String? = null,
    override var menuId: Int? = null,
    override var menuCreatedListener: ((Menu) -> Unit)? = null,
    override var itemSelectedListener: ((MenuItem) -> Boolean)? = null,
) : AppBarConfigurationChange