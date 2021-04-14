package com.flaringapp.app

import android.app.Application
import com.flaringapp.app.di.DataModule
import com.flaringapp.app.di.NetworkModule
import com.flaringapp.app.di.PresentationModule
import com.flaringapp.app.di.RepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ApplicationImpl: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ApplicationImpl)
            modules(NetworkModule, DataModule, RepositoryModule, PresentationModule)
        }
    }

}