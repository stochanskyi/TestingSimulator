package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepository
import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.UserLoginMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.UserLoginMapperImpl
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    factory<UserAuthRepository> { UserAuthRepositoryImpl(get(), get()) }
    factory<UserLoginMapper> { UserLoginMapperImpl() }

    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }

    factory<UserProfileRepository> { UserProfileRepositoryImpl(get(), get()) }

}