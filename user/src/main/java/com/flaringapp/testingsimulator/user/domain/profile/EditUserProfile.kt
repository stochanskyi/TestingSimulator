package com.flaringapp.testingsimulator.user.domain.profile

import com.flaringapp.testingsimulator.domain.features.profile.EditProfile

class EditUserProfile(
    override val firstName: String,
    override val lastName: String,
    val studying: String?,
    val workPlace: String?,
    val role: String?,
) : EditProfile