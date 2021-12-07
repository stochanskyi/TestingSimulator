package com.flaringapp.testingsimulator.core.presentation.appbar.menu

interface AppBarMenuLazyConsumer : AppBarMenuConsumer {

    fun provideAppBarMenuContracts(): AppBarMenuContracts

}