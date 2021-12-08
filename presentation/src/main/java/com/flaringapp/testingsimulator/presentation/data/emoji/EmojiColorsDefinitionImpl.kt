package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsRes
import com.flaringapp.testingsimulator.presentation.R

class EmojiColorsDefinitionImpl : EmojiColorsDefinition {

    private val emojiColorsMap = mapOf(
        R.drawable.img_emoji_test to EmojiColorsRes(
            background = R.color.statistics_test_bg,
            accent = R.color.statistics_test_accent,
            variant = R.color.statistics_test_variant,
        ),
        R.drawable.img_emoji_student to EmojiColorsRes(
            background = R.color.statistics_student_bg,
            accent = R.color.statistics_student_accent,
            variant = R.color.statistics_student_variant,
        ),
        R.drawable.img_emoji_eyes to EmojiColorsRes(
            background = R.color.statistics_eyes_bg,
            accent = R.color.statistics_eyes_accent,
            variant = R.color.statistics_eyes_variant,
        ),
        R.drawable.img_emoji_brain to EmojiColorsRes(
            background = R.color.statistics_brain_bg,
            accent = R.color.statistics_brain_accent,
            variant = R.color.statistics_brain_variant,
        ),
        R.drawable.img_emoji_win to EmojiColorsRes(
            background = R.color.statistics_win_bg,
            accent = R.color.statistics_win_accent,
            variant = R.color.statistics_win_variant,
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