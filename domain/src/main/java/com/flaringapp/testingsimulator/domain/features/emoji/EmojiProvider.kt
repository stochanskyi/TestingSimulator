package com.flaringapp.testingsimulator.domain.features.emoji

interface EmojiProvider {

    fun getEmoji(emojiId: Int): Int?

    fun getEmojiOrDefault(emojiId: Int): Int

}