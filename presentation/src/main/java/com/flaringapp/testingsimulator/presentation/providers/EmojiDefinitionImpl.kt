package com.flaringapp.testingsimulator.presentation.providers

import com.flaringapp.testingsimulator.presentation.R

class EmojiDefinitionImpl : EmojiDefinition {

    //TODO fill with emojis
    private val emojisMap = mapOf(
        1 to R.drawable.ic_emoji_testing_enabled
    )

    //TODO replace with real default emoji
    private val defaultEmoji = R.drawable.ic_emoji_testing_enabled

    override fun getEmojiById(emojiId: Int): Int? {
        return emojisMap[emojiId]
    }

    override fun getEmojiByIdOrDefault(emojiId: Int): Int {
        return getEmojiById(emojiId) ?: defaultEmoji
    }

}