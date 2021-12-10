package com.flaringapp.testingsimulator.presentation.data.emoji

import com.flaringapp.testingsimulator.presentation.R

class EmojiDefinitionImpl : EmojiDefinition {

    private val emojisMap = mapOf(
        1 to R.drawable.img_emoji_testing_enabled, // Tests enabled
        2 to R.drawable.img_emoji_testing_disabled, // Tests disabled
        3 to R.drawable.img_emoji_development_enabled, // Development enabled
        4 to R.drawable.img_emoji_development_disabled, // Development disabled
        5 to R.drawable.img_emoji_design_enabled, // Design enabled
        6 to R.drawable.img_emoji_design_disabled, // Design disabled
        7 to R.drawable.img_emoji_project_management_enabled, // PM enabled
        8 to R.drawable.img_emoji_project_management_disabled, // PM disabled
        14 to R.drawable.img_emoji_requirements_enabled, // Requirements enabled
        15 to R.drawable.img_emoji_requirements_disabled, // Requirements disabled
        9 to R.drawable.img_emoji_brain, // Passed tasks for user, Success rate for admin
        10 to R.drawable.img_emoji_eyes, // Task tries for user, Attempts for admin
        11 to R.drawable.img_emoji_note, // Tests taken for user, Tests published for admin
        12 to R.drawable.img_emoji_student, // Passed tests for user, Students for admin
        13 to R.drawable.img_emoji_win, // Average rate for user, - for admin
    )

    private val defaultEmoji = R.drawable.img_emoji_eyes

    override fun getEmojiById(emojiId: Int): Int? {
        return emojisMap[emojiId]
    }

    override fun getEmojiByIdOrDefault(emojiId: Int): Int {
        return getEmojiById(emojiId) ?: defaultEmoji
    }

}