package com.flaringapp.testingsimulator.user.app.di

import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepository
import com.flaringapp.testingsimulator.user.data.repository.auth.UserAuthRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepository
import com.flaringapp.testingsimulator.user.data.repository.UserDataRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.UserLoginMapper
import com.flaringapp.testingsimulator.user.data.repository.auth.mappers.UserLoginMapperImpl
import com.flaringapp.testingsimulator.user.data.repository.profile.UserEditProfileMapper
import com.flaringapp.testingsimulator.user.data.repository.profile.UserEditProfileMapperImpl
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepository
import com.flaringapp.testingsimulator.user.data.repository.profile.UserProfileRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestMapper
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestMapperImpl
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepository
import com.flaringapp.testingsimulator.user.data.repository.tests.UserTestsRepositoryImpl
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestDetailsMapper
import com.flaringapp.testingsimulator.user.data.repository.tests.mappers.UserTestDetailsMapperImpl
import org.koin.dsl.module

val RepositoryModule = module {

    factory<UserAuthRepository> { UserAuthRepositoryImpl(get(), get()) }
    factory<UserLoginMapper> { UserLoginMapperImpl() }

    factory<UserDataRepository> { UserDataRepositoryImpl(get()) }

    factory<UserProfileRepository> { UserProfileRepositoryImpl(get(), get(), get(), get()) }
    factory<UserEditProfileMapper> { UserEditProfileMapperImpl() }


    factory<UserTestsRepository> { UserTestsRepositoryImpl(get(), get(), get()) }
    factory<UserTestMapper> { UserTestMapperImpl() }
    factory<UserTestDetailsMapper> { UserTestDetailsMapperImpl() }

}