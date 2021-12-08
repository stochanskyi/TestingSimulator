package com.flaringapp.testingsimulator.presentation

import com.flaringapp.testingsimulator.presentation.data.emoji.EmojiDefinition
import com.flaringapp.testingsimulator.presentation.data.emoji.EmojiDefinitionImpl
import org.koin.dsl.module

val PresentationDefinitionsModule = module {

    single<EmojiDefinition> { EmojiDefinitionImpl() }

}