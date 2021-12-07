package com.flaringapp.testingsimulator.presentation.appbar.menu

import android.view.MenuItem

class AppBarMenuContract(
    val itemId: Int,
    val handleAction: (MenuItem) -> Unit
)