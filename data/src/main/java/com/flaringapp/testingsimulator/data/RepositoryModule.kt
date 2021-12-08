package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsMapper
import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsMapperImpl
import com.flaringapp.testingsimulator.data.repository.profile_statistics.ProfileStatisticsRepositoryImpl
import com.flaringapp.testingsimulator.data.repository.topics.TopicsMapper
import com.flaringapp.testingsimulator.data.repository.topics.TopicsMapperImpl
import com.flaringapp.testingsimulator.data.repository.topics.TopicsRepositoryImpl
import com.flaringapp.testingsimulator.domain.features.profile_statistics.ProfileStatisticsRepository
import com.flaringapp.testingsimulator.domain.features.topics.TopicsRepository
import org.koin.dsl.module

val RepositoryModule = module {

    factory<ProfileStatisticsRepository> { ProfileStatisticsRepositoryImpl(get(), get()) }
    factory<ProfileStatisticsMapper> { ProfileStatisticsMapperImpl() }

    factory<TopicsRepository> { TopicsRepositoryImpl(get(), get()) }
    factory<TopicsMapper> { TopicsMapperImpl() }
}