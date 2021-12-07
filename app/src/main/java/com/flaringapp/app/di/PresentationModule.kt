package com.flaringapp.app.di

import com.flaringapp.presentation.features.auth.login.LoginViewModel
import com.flaringapp.presentation.features.auth.login.LoginViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel<LoginViewModel> { LoginViewModelImpl(get()) }

}