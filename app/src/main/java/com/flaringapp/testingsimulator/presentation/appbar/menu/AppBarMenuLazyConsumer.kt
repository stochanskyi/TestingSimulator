package com.flaringapp.testingsimulator.presentation.appbar.menu

interface AppBarMenuLazyConsumer : AppBarMenuConsumer {

    fun provideAppBarMenuContracts(): AppBarMenuContracts

}