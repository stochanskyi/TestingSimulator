package com.flaringapp.testingsimulator.admin.presentation.profile

import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviourGetProfileConsumer
import kotlinx.coroutines.Dispatchers

class AdminProfileBehaviour(
    private val textProvider: TextProvider,
    private val getProfileUseCase: GetProfileUseCase<AdminProfile>
) : ProfileBehaviour {

    override suspend fun loadProfile(
        consumer: ProfileBehaviourGetProfileConsumer
    ): CallResultNothing {
        return getProfileUseCase()
            .doOnSuccessSuspend(Dispatchers.Main) {
                processProfileData(consumer, it)
            }
            .ignoreData()
    }

    private fun processProfileData(
        consumer: ProfileBehaviourGetProfileConsumer,
        profile: AdminProfile
    ) {
        consumer.handleProfileData(
            name = textProvider.formatName(profile.firstName, profile.lastName),
            email = profile.email,
            workPlace = profile.workPlace,
            role = profile.role
        )
    }
}