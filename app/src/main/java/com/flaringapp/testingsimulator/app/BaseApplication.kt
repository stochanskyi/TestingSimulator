package com.flaringapp.testingsimulator.app

import android.app.Application
import com.flaringapp.testingsimulator.app.di.*
import com.flaringapp.testingsimulator.data.DataModule
import com.flaringapp.testingsimulator.data.NetworkModule
import com.flaringapp.testingsimulator.domain.DomainModule
import com.flaringapp.testingsimulator.domain.UseCaseModule
import com.flaringapp.testingsimulator.presentation.PresentationDomainBindingModule
import com.flaringapp.testingsimulator.presentation.PresentationDefinitionsModule
import com.flaringapp.testingsimulator.presentation.PresentationModule
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
                ApplicationModule,
                NetworkModule,
                DataModule,
                DomainModule,
                UseCaseModule,
                ApplicationPresentationModule,
                PresentationDefinitionsModule,
                PresentationDomainBindingModule,
                PresentationModule,
                *provideAdditionalModules()
            )
        }
    }

    protected abstract fun provideAdditionalModules(): Array<Module>

}