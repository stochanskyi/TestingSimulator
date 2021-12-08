package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.domain.features.profile.Profile

class UserProfile(
    override val id: String,
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    val studying: String?,
    val workPlace: String?,
    val role: String?,
) : Profile