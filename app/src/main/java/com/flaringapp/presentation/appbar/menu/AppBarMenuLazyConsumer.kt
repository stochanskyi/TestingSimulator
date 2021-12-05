package com.flaringapp.presentation.appbar.menu

interface AppBarMenuLazyConsumer : AppBarMenuConsumer {

    fun provideAppBarMenuContracts(): AppBarMenuContracts

}