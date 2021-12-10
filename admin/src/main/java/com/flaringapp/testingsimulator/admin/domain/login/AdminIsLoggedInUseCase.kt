package com.flaringapp.testingsimulator.admin.domain.login

import com.flaringapp.testingsimulator.admin.data.repository.AdminDataRepository
import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase

class AdminIsLoggedInUseCase(
    private val dataRepository: AdminDataRepository,
): IsLoggedInUseCase {

    override fun invoke(): Boolean {
        return dataRepository.remember && dataRepository.token != null
    }
}