package com.flaringapp.testingsimulator.app.di

import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.color.ColorProviderImpl
import com.flaringapp.testingsimulator.core.data.datetime.DateTimeFormats
import com.flaringapp.testingsimulator.core.data.datetime.DateTimeFormatsImpl
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.data.textprovider.TextProviderImpl
import com.flaringapp.testingsimulator.data.storage.DataStorage
import com.flaringapp.testingsimulator.data.storage.DataStorageImpl
import org.koin.dsl.module

val DataModule = module {

    single<DataStorage> { DataStorageImpl(get()) }

    single<TextProvider> { TextProviderImpl(get()) }

    single<ColorProvider> { ColorProviderImpl(get()) }

    single<DateTimeFormats> { DateTimeFormatsImpl() }

}