package com.flaringapp.app.di

import com.flaringapp.data.color.ColorProvider
import com.flaringapp.data.color.ColorProviderImpl
import com.flaringapp.data.datetime.DateTimeFormats
import com.flaringapp.data.datetime.DateTimeFormatsImpl
import com.flaringapp.data.storage.DataStorage
import com.flaringapp.data.storage.DataStorageImpl
import com.flaringapp.data.text.TextProvider
import com.flaringapp.data.text.TextProviderImpl
import com.flaringapp.data.usecase.auth.LoginUseCase
import com.flaringapp.data.usecase.auth.UserLoginUseCase
import org.koin.dsl.module

val DataModule = module {

    single<DataStorage> { DataStorageImpl(get()) }

    single<TextProvider> { TextProviderImpl(get()) }

    single<ColorProvider> { ColorProviderImpl(get()) }

    single<DateTimeFormats> { DateTimeFormatsImpl() }

    factory<LoginUseCase> { UserLoginUseCase() }

}