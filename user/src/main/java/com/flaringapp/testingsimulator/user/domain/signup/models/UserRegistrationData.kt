package com.flaringapp.testingsimulator.user.domain.signup.models

class UserRegistrationData(
    val email: String,
    val firstName: String,
    val lastName: String,
    val studyingAt: String,
    val workPlace: String,
    val role: String,
    val password: String,
    val repeatPassword: String,
)