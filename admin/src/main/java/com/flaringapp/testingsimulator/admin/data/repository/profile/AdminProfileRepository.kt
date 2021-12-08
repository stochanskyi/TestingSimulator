package com.flaringapp.testingsimulator.admin.data.repository.profile

import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorage
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminProfileRepository {

    suspend fun getProfile(): CallResult<AdminProfile>

    suspend fun saveProfile(profile: AdminProfile)

}

class AdminProfileRepositoryImpl(
    private val dataStorage: DataStorage,
    private val profileDataStorage: AdminProfileDataStorage,
) : AdminProfileRepository {

    override suspend fun getProfile(): CallResult<AdminProfile> {
        val profile = createProfileFromStorage()
            ?: return CallResult.Error("Error loading admin profile")

        return CallResult.Success(profile)
    }

    override suspend fun saveProfile(profile: AdminProfile) {
        profile.saveIntoStorage()
    }

    private fun createProfileFromStorage(): AdminProfile? {
        return AdminProfile(
            id = dataStorage.userId,
            firstName = profileDataStorage.firstName ?: return null,
            lastName = profileDataStorage.lastName ?: return null,
            email = profileDataStorage.email ?: return null,
            workPlace = profileDataStorage.workPlace,
            role = profileDataStorage.role,
        )
    }

    private fun AdminProfile.saveIntoStorage() {
        dataStorage.userId = id
        with(profileDataStorage) {
            firstName = firstName
            lastName = lastName
            email = email
            workPlace = workPlace
            role = role
        }
    }
}