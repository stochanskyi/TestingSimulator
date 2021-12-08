package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorage
import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorageImpl
import org.koin.dsl.module

val DataModule = module {

    single<AdminProfileDataStorage> { AdminProfileDataStorageImpl(get()) }

}