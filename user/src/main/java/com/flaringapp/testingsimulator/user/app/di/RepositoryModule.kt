package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.repository.AuthRepository
import com.flaringapp.testingsimulator.user.data.repository.AuthRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.LoginResponseMapperImpl
import org.koin.dsl.module

val RepositoryModule = module {

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory<LoginResponseMapper> { LoginResponseMapperImpl() }

    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }
}