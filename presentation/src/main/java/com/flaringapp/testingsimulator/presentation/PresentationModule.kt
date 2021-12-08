package com.flaringapp.testingsimulator.presentation

import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewModel
import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewModelImpl
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpVewModel
import com.flaringapp.testingsimulator.presentation.features.auth.signup.SignUpVewModelImpl
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileViewModel
import com.flaringapp.testingsimulator.presentation.features.profile.ProfileViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<LoginViewModel> { LoginViewModelImpl(get(), get(), get(), get()) }

    viewModel<SignUpVewModel> { SignUpVewModelImpl(get(), get(), get(), get(), get(), get()) }

    viewModel<ProfileViewModel> { ProfileViewModelImpl() }

}