package com.flaringapp.testingsimulator.app

import com.flaringapp.testingsimulator.admin.app.di.DataModule
import com.flaringapp.testingsimulator.admin.app.di.NetworkModule
import com.flaringapp.testingsimulator.admin.app.di.PresentationModule
import com.flaringapp.testingsimulator.admin.app.di.RepositoryModule
import com.flaringapp.testingsimulator.admin.app.di.UseCaseModule
import org.koin.core.module.Module

class AdminApplication : BaseApplication() {

    override fun provideAdditionalModules(): Array<Module> = arrayOf(
        DataModule,
        NetworkModule,
        RepositoryModule,
        UseCaseModule,
        PresentationModule,
    )

}