package com.flaringapp.testingsimulator.presentation.providers

interface EmojiDefinition {
    fun getEmojiById(emojiId: Int): Int?

    fun getEmojiByIdOrDefault(emojiId: Int): Int
}
