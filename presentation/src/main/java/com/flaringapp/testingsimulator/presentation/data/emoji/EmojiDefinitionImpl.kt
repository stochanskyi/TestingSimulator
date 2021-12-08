package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.presentation.R

class EmojiDefinitionImpl : EmojiDefinition {

    //TODO fill with emojis
    private val emojisMap = mapOf(
        1 to R.drawable.img_emoji_testing_enabled
    )

    //TODO replace with real default emoji
    private val defaultEmoji = R.drawable.img_emoji_testing_enabled

    override fun getEmojiById(emojiId: Int): Int? {
        return emojisMap[emojiId]
    }

    override fun getEmojiByIdOrDefault(emojiId: Int): Int {
        return getEmojiById(emojiId) ?: defaultEmoji
    }

}