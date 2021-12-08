package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsMapper
import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsMapperImpl
import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsRepositoryImpl
import com.flaringapp.testingsimulator.domain.features.profile_statistics.ProfileStatisticsRepository
import org.koin.dsl.module

val RepositoryModule = module {

    factory<ProfileStatisticsRepository> { ProfileStatisticsRepositoryImpl(get(), get()) }
    factory<ProfileStatisticsMapper> { ProfileStatisticsMapperImpl() }

}