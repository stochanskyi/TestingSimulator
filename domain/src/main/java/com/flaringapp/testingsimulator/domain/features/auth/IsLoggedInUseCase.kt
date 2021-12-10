package com.flaringapp.testingsimulator.domain.features.auth

interface IsLoggedInUseCase {
    operator fun invoke(): Boolean
}