package com.flaringapp.testingsimulator.presentation

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider
import com.flaringapp.testingsimulator.presentation.data.emoji.EmojiProviderImpl
import org.koin.dsl.module

val PresentationDomainBindingModule = module {

    factory<EmojiProvider> { EmojiProviderImpl(get()) }

}