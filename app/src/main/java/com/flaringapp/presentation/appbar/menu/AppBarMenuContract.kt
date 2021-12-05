package com.flaringapp.presentation.appbar.menu

import android.view.MenuItem

class AppBarMenuContract(
    val itemId: Int,
    val handleAction: (MenuItem) -> Unit
)