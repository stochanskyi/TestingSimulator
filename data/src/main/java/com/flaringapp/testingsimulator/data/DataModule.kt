package com.flaringapp.testingsimulator.data

import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.color.ColorProviderImpl
import com.flaringapp.testingsimulator.core.data.datetime.DateTimeFormats
import com.flaringapp.testingsimulator.core.data.datetime.DateTimeFormatsImpl
import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifierAnnotationProcessor
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.data.textprovider.TextProviderImpl
import com.flaringapp.testingsimulator.data.network.modifiers.CommonRequestModifierAnnotationProcessor
import com.flaringapp.testingsimulator.data.storage.DataStorageImpl
import com.flaringapp.testingsimulator.domain.storage.DataStorage
import org.koin.dsl.module

val DataModule = module {

    single<RequestModifierAnnotationProcessor> { CommonRequestModifierAnnotationProcessor() }

    single<DataStorage> { DataStorageImpl(get()) }

    single<TextProvider> { TextProviderImpl(get()) }

    single<ColorProvider> { ColorProviderImpl(get()) }

    single<DateTimeFormats> { DateTimeFormatsImpl() }

}