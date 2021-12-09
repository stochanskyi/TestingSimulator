package com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour

interface EditProfileBehaviourGetProfileConsumer {

    fun handleProfileData(
        firstName: String,
        lastName: String,
        studying: String? = null,
        workPlace: String? = null,
        role: String? = null,
    )

}