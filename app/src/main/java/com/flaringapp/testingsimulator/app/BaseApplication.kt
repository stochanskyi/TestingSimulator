package com.flaringapp.testingsimulator.app

import android.app.Application
import com.flaringapp.testingsimulator.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                NetworkModule,
                DataModule,
                RepositoryModule,
                PresentationModule,
                UseCaseModule,
                *provideAdditionalModules()
            )
        }
    }

    protected abstract fun provideAdditionalModules(): Array<Module>

}