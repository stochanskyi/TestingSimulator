package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.data.repository.auth.AdminAuthRepository
import com.flaringapp.testingsimulator.admin.data.repository.auth.AdminAuthRepositoryImpl
import com.flaringapp.testingsimulator.admin.data.repository.auth.mappers.AdminLoginMapper
import com.flaringapp.testingsimulator.admin.data.repository.auth.mappers.AdminLoginMapperImpl
import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository
import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepositoryImpl
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminEditProfileMapper
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminEditProfileMapperImpl
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepository
import com.flaringapp.testingsimulator.admin.data.repository.profile.AdminProfileRepositoryImpl
import com.flaringapp.testingsimulator.admin.data.repository.tasks.AdminTasksRepository
import com.flaringapp.testingsimulator.admin.data.repository.tasks.AdminTasksRepositoryImpl
import com.flaringapp.testingsimulator.admin.data.repository.tasks.mappers.*
import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestMapper
import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestMapperImpl
import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepository
import com.flaringapp.testingsimulator.admin.data.repository.tests.AdminTestsRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    factory<AdminAuthRepository> { AdminAuthRepositoryImpl(get(), get()) }
    factory<AdminLoginMapper> { AdminLoginMapperImpl() }

    factory<AdminDataRepository> { AdminDataRepositoryImpl(get()) }

    factory<AdminProfileRepository> { AdminProfileRepositoryImpl(get(), get(), get(), get()) }
    factory<AdminEditProfileMapper> { AdminEditProfileMapperImpl() }

    factory<AdminTestsRepository> { AdminTestsRepositoryImpl(get(), get()) }
    factory<AdminTestMapper> { AdminTestMapperImpl() }

    factory<AdminTaskDetailedMapper> { AdminTaskDetailedMapperImpl() }
    factory<AdminAddBlockMapper> { AdminAddBlockMapperImpl() }
    factory<AdminTaskEditionMapper> { AdminTaskEditionMapperImpl(get()) }
    factory<AdminTasksRepository> { AdminTasksRepositoryImpl(get(), get(), get(), get()) }
}