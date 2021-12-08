package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.repository.auth.AuthRepository
import com.flaringapp.testingsimulator.user.data.repository.auth.AuthRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapperImpl
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<LoginResponseMapper> { LoginResponseMapperImpl() }

    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }

    factory<UserProfileRepository> { UserProfileRepositoryImpl(get(), get()) }

}