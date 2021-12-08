package com.flaringapp.testingsimulator.admin.data.repository.profile

import com.flaringapp.testingsimulator.admin.data.storage.profile.AdminProfileDataStorage
import com.flaringapp.testingsimulator.admin.domain.profile.AdminProfile
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.domain.storage.DataStorage

interface AdminProfileRepository {

    suspend fun getProfile(): CallResult<AdminProfile>

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

    private fun createProfileFromStorage(): AdminProfile? {
        return AdminProfile(
            id = dataStorage.userId ?: return null,
            firstName = profileDataStorage.firstName ?: return null,
            lastName = profileDataStorage.lastName ?: return null,
            email = profileDataStorage.email ?: return null,
            workPlace = profileDataStorage.workPlace,
            role = profileDataStorage.role,
        )
    }
}