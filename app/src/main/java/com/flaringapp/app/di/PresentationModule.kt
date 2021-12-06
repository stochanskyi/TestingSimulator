package com.flaringapp.app.di

import com.flaringapp.presentation.features.auth.login.AuthViewModel
import com.flaringapp.presentation.features.auth.login.AuthViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<AuthViewModel> { AuthViewModelImpl(get()) }

}