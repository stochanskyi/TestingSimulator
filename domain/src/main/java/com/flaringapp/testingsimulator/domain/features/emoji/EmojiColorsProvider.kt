package com.flaringapp.testingsimulator.domain.features.emoji

interface EmojiColorsProvider {

    fun getEmojiColors(emojiId: Int): EmojiColorsRes?

    fun getEmojiColorsOrDefault(emojiId: Int): EmojiColorsRes

}