package com.flaringapp.app.di

import com.flaringapp.data.text.TextProvider
import com.flaringapp.data.text.TextProviderImpl
import org.koin.dsl.module

val DataModule = module {

    single<TextProvider> { TextProviderImpl(get()) }

}