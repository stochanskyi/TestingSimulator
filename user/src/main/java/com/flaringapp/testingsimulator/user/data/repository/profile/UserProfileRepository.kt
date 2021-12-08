package com.flaringapp.testingsimulator.user.data.repository.profile

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.storage.DataStorage
import com.flaringapp.testingsimulator.user.data.storage.profile.UserProfileDataStorage
import com.flaringapp.testingsimulator.user.domain.profile.UserProfile

interface UserProfileRepository {

    suspend fun getProfile(): CallResult<UserProfile>

}

class UserProfileRepositoryImpl(
    private val dataStorage: DataStorage,
    private val profileDataStorage: UserProfileDataStorage,
) : UserProfileRepository {

    override suspend fun getProfile(): CallResult<UserProfile> {
        val profile = createProfileFromStorage()
            ?: return CallResult.Error("Error loading user profile")

        return CallResult.Success(profile)
    }

    private fun createProfileFromStorage(): UserProfile? {
        return UserProfile(
            id = dataStorage.userId,
            firstName = profileDataStorage.firstName ?: return null,
            lastName = profileDataStorage.lastName ?: return null,
            email = profileDataStorage.email ?: return null,
            studying = profileDataStorage.studying,
            workPlace = profileDataStorage.workPlace,
            role = profileDataStorage.role,
        )
    }
}