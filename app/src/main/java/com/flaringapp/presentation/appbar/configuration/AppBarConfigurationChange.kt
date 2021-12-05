package com.flaringapp.presentation.appbar.configuration

import android.view.Menu
import android.view.MenuItem

interface AppBarConfigurationChange {

    var showNavigationButton: Boolean?

    var title: String?

    var menuId: Int?

    var menuCreatedListener: ((Menu) -> Unit)?

    var itemSelectedListener: ((MenuItem) -> Boolean)?

}