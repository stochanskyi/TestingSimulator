package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsRes
import com.flaringapp.testingsimulator.presentation.R

class EmojiColorsDefinitionImpl : EmojiColorsDefinition {

    //TODO fill with emojis
    private val emojiColorsMap = mapOf(
        R.drawable.img_emoji_test to EmojiColorsRes(
            background = R.color.statistics_test_bg,
            accent = R.color.statistics_test_accent,
            variant = R.color.statistics_test_variant,
        ),
    )

    private val defaultEmojiColors = EmojiColorsRes(
        background = R.color.statistics_test_bg,
        accent = R.color.statistics_test_accent,
        variant = R.color.statistics_test_variant,
    )

    override fun getEmojiColorsById(emojiId: Int): EmojiColorsRes? {
        return emojiColorsMap[emojiId]
    }

    override fun getEmojiColorsByIdOrDefault(emojiId: Int): EmojiColorsRes {
        return getEmojiColorsById(emojiId) ?: defaultEmojiColors
    }
}