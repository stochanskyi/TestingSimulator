package com.flaringapp.testingsimulator.core.presentation.views.input

class LettersOnlyInputFilter: CharacterInputFilter() {

    companion object {
        private val LETTER_RANGE = 'a'..'z'
        private val CAP_LETTER_RANGE = 'A'..'Z'
    }

    override fun matchesChar(char: Char): Boolean {
        return char in LETTER_RANGE || char in CAP_LETTER_RANGE
    }
}