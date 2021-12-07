package com.flaringapp.testingsimulator.presentation

import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewModel
import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<LoginViewModel> { LoginViewModelImpl(get(), get(), get(), get()) }

}