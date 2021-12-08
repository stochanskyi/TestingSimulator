package com.flaringapp.testingsimulator.app

import com.flaringapp.testingsimulator.admin.app.di.DataModule as AdminDataModule
import com.flaringapp.testingsimulator.admin.app.di.RepositoryModule as AdminRepositoryModule
import com.flaringapp.testingsimulator.admin.app.di.NetworkModule as AdminNetworkModule
import com.flaringapp.testingsimulator.admin.app.di.PresentationModule as AdminPresentationModule
import com.flaringapp.testingsimulator.admin.app.di.UseCaseModule as AdminUseCaseModule
import com.flaringapp.testingsimulator.user.app.di.DataModule as UserDataModule
import com.flaringapp.testingsimulator.user.app.di.RepositoryModule as UserRepositoryModule
import com.flaringapp.testingsimulator.user.app.di.NetworkModule as UserNetworkModule
import com.flaringapp.testingsimulator.user.app.di.PresentationModule as UserPresentationModule
import com.flaringapp.testingsimulator.user.app.di.UseCaseModule as UserUseCaseModule
import org.koin.core.module.Module

class SharedApplication : BaseApplication() {

    override fun provideAdditionalModules(): Array<Module> = arrayOf(
        AdminDataModule,
        AdminNetworkModule,
        AdminRepositoryModule,
        AdminUseCaseModule,
        AdminPresentationModule,
        UserDataModule,
        UserNetworkModule,
        UserRepositoryModule,
        UserUseCaseModule,
        UserPresentationModule,
    )

}