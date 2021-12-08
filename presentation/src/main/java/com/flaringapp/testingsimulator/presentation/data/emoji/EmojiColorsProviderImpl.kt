package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsProvider
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsRes

class EmojiColorsProviderImpl(
    private val emojiColorsDefinition: EmojiColorsDefinition,
) : EmojiColorsProvider {

    override fun getEmojiColors(emojiId: Int): EmojiColorsRes? {
        return emojiColorsDefinition.getEmojiColorsById(emojiId)
    }

    override fun getEmojiColorsOrDefault(emojiId: Int): EmojiColorsRes {
        return emojiColorsDefinition.getEmojiColorsByIdOrDefault(emojiId)
    }
}