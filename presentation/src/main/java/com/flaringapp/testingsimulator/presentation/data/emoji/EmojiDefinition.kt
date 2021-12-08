package com.flaringapp.testingsimulator.presentation.data.emoji

interface EmojiDefinition {

    fun getEmojiById(emojiId: Int): Int?

    fun getEmojiByIdOrDefault(emojiId: Int): Int

}
