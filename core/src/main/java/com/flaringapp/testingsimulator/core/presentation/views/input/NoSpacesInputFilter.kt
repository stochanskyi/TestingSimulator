package com.flaringapp.testingsimulator.core.presentation.views.input

class NoSpacesInputFilter: CharacterInputFilter() {

    override fun matchesChar(char: Char): Boolean {
        return !char.isWhitespace()
    }
}