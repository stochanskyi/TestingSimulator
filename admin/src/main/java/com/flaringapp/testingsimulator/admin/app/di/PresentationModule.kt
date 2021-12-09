package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.presentation.auth.login.AdminLoginViewBehaviour
import com.flaringapp.testingsimulator.admin.presentation.edit_profile.AdminEditProfileBehaviour
import com.flaringapp.testingsimulator.admin.presentation.profile.AdminProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour.EditProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import org.koin.dsl.module

val PresentationModule = module {

    factory<LoginViewBehaviour> { AdminLoginViewBehaviour() }

    factory<ProfileBehaviour> { AdminProfileBehaviour(get(), get()) }

    factory<EditProfileBehaviour> { AdminEditProfileBehaviour(get(), get()) }

}