package com.flaringapp.testingsimulator.app

import com.flaringapp.testingsimulator.user.app.di.DataModule
import com.flaringapp.testingsimulator.user.app.di.RepositoryModule
import com.flaringapp.testingsimulator.user.app.di.NetworkModule
import com.flaringapp.testingsimulator.user.app.di.PresentationModule
import com.flaringapp.testingsimulator.user.app.di.UseCaseModule
import org.koin.core.module.Module

class UserApplication : BaseApplication() {

    override fun provideAdditionalModules(): Array<Module> = arrayOf(
        DataModule,
        NetworkModule,
        RepositoryModule,
        UseCaseModule,
        PresentationModule,
    )

}