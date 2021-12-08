package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.storage.profile.UserProfileDataStorage
import com.flaringapp.testingsimulator.user.data.storage.profile.UserProfileDataStorageImpl
import org.koin.dsl.module

val DataModule = module {

    single<UserProfileDataStorage> { UserProfileDataStorageImpl(get()) }

}