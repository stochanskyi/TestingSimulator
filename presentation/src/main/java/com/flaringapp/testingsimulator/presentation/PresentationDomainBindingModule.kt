package com.flaringapp.testingsimulator.presentation

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsProvider
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider
import com.flaringapp.testingsimulator.presentation.data.emoji.EmojiColorsProviderImpl
import com.flaringapp.testingsimulator.presentation.data.emoji.EmojiProviderImpl
import org.koin.dsl.module

val PresentationDomainBindingModule = module {

    factory<EmojiProvider> { EmojiProviderImpl(get()) }

    factory<EmojiColorsProvider> { EmojiColorsProviderImpl(get()) }

}