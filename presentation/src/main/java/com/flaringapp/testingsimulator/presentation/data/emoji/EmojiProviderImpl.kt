package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider

class EmojiProviderImpl(
    private val emojiDefinition: EmojiDefinition
) : EmojiProvider {

    override fun getEmoji(emojiId: Int): Int? {
        return emojiDefinition.getEmojiById(emojiId)
    }

    override fun getEmojiOrDefault(emojiId: Int): Int {
        return emojiDefinition.getEmojiByIdOrDefault(emojiId)
    }

}