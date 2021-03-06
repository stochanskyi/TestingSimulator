package com.flaringapp.testingsimulator.admin.app.di

import com.flaringapp.testingsimulator.admin.presentation.auth.login.AdminLoginViewBehaviour
import com.flaringapp.testingsimulator.admin.presentation.navigation.AdminNavigationGraphProvider
import com.flaringapp.testingsimulator.admin.presentation.edit_profile.AdminEditProfileBehaviour
import com.flaringapp.testingsimulator.admin.presentation.edit_profile.AdminProfileNavigator
import com.flaringapp.testingsimulator.admin.presentation.navigation.tests.AdminTestsFragmentArgsHandler
import com.flaringapp.testingsimulator.admin.presentation.profile.AdminProfileBehaviour
import com.flaringapp.testingsimulator.admin.presentation.task_edit.AdminTaskEditViewModel
import com.flaringapp.testingsimulator.admin.presentation.task_edit.AdminTaskEditViewModelImpl
import com.flaringapp.testingsimulator.admin.presentation.task_view.AdminViewTaskViewModel
import com.flaringapp.testingsimulator.admin.presentation.task_view.AdminViewTaskViewModelImpl
import com.flaringapp.testingsimulator.admin.presentation.test_details.AdminTestDetailsViewModeImpl
import com.flaringapp.testingsimulator.admin.presentation.test_details.AdminTestDetailsViewModel
import com.flaringapp.testingsimulator.admin.presentation.test_details.AdminTestStatusActionTransformer
import com.flaringapp.testingsimulator.admin.presentation.test_details.AdminTestStatusChangeUseCaseProvider
import com.flaringapp.testingsimulator.admin.presentation.tests.*
import com.flaringapp.testingsimulator.admin.presentation.topics.AdminTopicsNavigator
import com.flaringapp.testingsimulator.presentation.features.auth.login.LoginViewBehaviour
import com.flaringapp.testingsimulator.presentation.features.topics.navigation.TopicsNavigator
import com.flaringapp.testingsimulator.presentation.navigation.NavigationGraphProvider
import com.flaringapp.testingsimulator.presentation.features.edit_profile.behaviour.EditProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.navigation.ProfileNavigator
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TestsFragmentArgsHandler
import com.flaringapp.testingsimulator.presentation.features.tests.behaviour.TestsBehaviour
import com.flaringapp.testingsimulator.presentation.features.tests.navigation.TestsNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    factory<NavigationGraphProvider> { AdminNavigationGraphProvider() }

    factory<TopicsNavigator> { AdminTopicsNavigator() }

    factory<LoginViewBehaviour> { AdminLoginViewBehaviour() }

    factory<ProfileNavigator> { AdminProfileNavigator() }
    factory<ProfileBehaviour> { AdminProfileBehaviour(get(), get()) }

    factory<EditProfileBehaviour> { AdminEditProfileBehaviour(get(), get()) }

    factory<TestsNavigator> { AdminTestsNavigator() }
    factory<TestsFragmentArgsHandler> { AdminTestsFragmentArgsHandler() }
    factory<TestsBehaviour> { AdminTestsBehaviour(get(), get(), get(), get(), get()) }
    factory { AdminTestStatusNameTransformer(get()) }
    factory { AdminTestStatusColorTransformer(get()) }

    viewModel<AdminTestDetailsViewModel> {
        AdminTestDetailsViewModeImpl(get(), get(), get(), get(), get(), get(), get(), get())
    }
    factory { AdminTestStatusIsEditableTransformer() }
    factory { AdminTestStatusIsEditTaskEnabledTransformer() }
    factory { AdminTestStatusActionTransformer(get()) }
    factory { AdminTestStatusChangeUseCaseProvider(get(), get()) }

    viewModel<AdminTaskEditViewModel> { AdminTaskEditViewModelImpl(get(), get(), get()) }

    viewModel<AdminViewTaskViewModel> { AdminViewTaskViewModelImpl(get()) }

}