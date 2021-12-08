package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsRes

interface EmojiColorsDefinition {

    fun getEmojiColorsById(emojiId: Int): EmojiColorsRes?

    fun getEmojiColorsByIdOrDefault(emojiId: Int): EmojiColorsRes

}
