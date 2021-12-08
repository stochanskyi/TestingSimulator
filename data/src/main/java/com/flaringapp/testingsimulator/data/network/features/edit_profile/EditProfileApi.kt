package com.flaringapp.testingsimulator.data.network.features.edit_profile

import com.flaringapp.testingsimulator.core.data.network.base.ApiResponse
import com.flaringapp.testingsimulator.data.network.features.edit_profile.request.EditProfileRequest
import com.flaringapp.testingsimulator.data.network.features.edit_profile.response.EditProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface EditProfileApi {

    // TODO API change route
    @POST("")
    suspend fun editProfile(
        @Body request: EditProfileRequest
    ) : ApiResponse<EditProfileResponse>

}