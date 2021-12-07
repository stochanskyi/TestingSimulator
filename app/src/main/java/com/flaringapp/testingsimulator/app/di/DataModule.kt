package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.data.color.ColorProvider
import com.flaringapp.testingsimulator.data.color.ColorProviderImpl
import com.flaringapp.testingsimulator.data.datetime.DateTimeFormats
import com.flaringapp.testingsimulator.data.datetime.DateTimeFormatsImpl
import com.flaringapp.testingsimulator.data.storage.DataStorage
import com.flaringapp.testingsimulator.data.storage.DataStorageImpl
import com.flaringapp.testingsimulator.data.text.TextProvider
import com.flaringapp.testingsimulator.data.text.TextProviderImpl
import org.koin.dsl.module

val DataModule = module {

    single<DataStorage> { DataStorageImpl(get()) }

    single<TextProvider> { TextProviderImpl(get()) }

    single<ColorProvider> { ColorProviderImpl(get()) }

    single<DateTimeFormats> { DateTimeFormatsImpl() }

}