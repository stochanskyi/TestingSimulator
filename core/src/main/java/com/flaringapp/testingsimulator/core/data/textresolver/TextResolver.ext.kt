package com.flaringapp.testingsimulator.core.data.textresolver

fun Int.asResolver() = TextResourceResolver(this)

fun CharSequence.asResolver() = TextNamedResolver(this)