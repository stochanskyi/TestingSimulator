package com.flaringapp.testingsimulator.presentation.features.profile

interface ProfileBehaviourGetProfileConsumer {

    fun handleProfileData(
        name: String,
        email: String,
        studying: String? = null,
        workPlace: String? = null,
        role: String? = null,
    )

}