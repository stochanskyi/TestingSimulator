package com.flaringapp.testingsimulator.presentation.features.tests.models

data class TestViewData(
    val id: Int,
    val name: CharSequence,
    val description: CharSequence,
    val status: CharSequence,
    val statusColor: Int,
)