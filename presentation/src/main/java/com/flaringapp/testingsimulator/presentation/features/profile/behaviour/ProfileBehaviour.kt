package com.flaringapp.testingsimulator.presentation.features.profile.behaviour

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing

interface ProfileBehaviour {

    suspend fun loadProfile(consumer: ProfileBehaviourGetProfileConsumer): CallResultNothing

}