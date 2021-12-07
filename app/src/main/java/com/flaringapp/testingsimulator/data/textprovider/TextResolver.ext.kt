package com.flaringapp.testingsimulator.data.textprovider

fun Int.asResolver() = TextResourceResolver(this)

fun CharSequence.asResolver() = TextNamedResolver(this)