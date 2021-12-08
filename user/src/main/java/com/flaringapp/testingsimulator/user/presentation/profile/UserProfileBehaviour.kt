package com.flaringapp.testingsimulator.user.presentation.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.domain.features.profile.GetProfileUseCase
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileBehaviourGetProfileConsumer
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile
import kotlinx.coroutines.Dispatchers

class UserProfileBehaviour(
    private val textProvider: TextProvider,
    private val getProfileUseCase: GetProfileUseCase<UserProfile>
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
        profile: UserProfile
    ) {
        consumer.handleProfileData(
            name = textProvider.formatName(profile.firstName, profile.lastName),
            email = profile.email,
            studying = profile.studying,
            workPlace = profile.workPlace,
            role = profile.role
        )
    }
}