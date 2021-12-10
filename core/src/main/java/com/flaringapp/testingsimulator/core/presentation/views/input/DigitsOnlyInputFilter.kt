package com.flaringapp.testingsimulator.core.presentation.views.input

class DigitsOnlyInputFilter: CharacterInputFilter() {

    companion object {
        private val NUMBER_RANGE = '0'..'9'
    }

    override fun matchesChar(char: Char): Boolean {
        return char in NUMBER_RANGE
    }
}