package com.flaringapp.testingsimulator.presentation.features.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.features.auth.ValidateEmailUseCase
import com.flaringapp.testingsimulator.domain.features.auth.ValidatePasswordUseCase
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class LoginViewModel : BaseViewModel() {

    abstract val emailLiveData: LiveData<String>
    abstract val passwordLiveData: LiveData<String>
    abstract val rememberMeLiveData: LiveData<Boolean>

    abstract val signUpEnabledLiveData: LiveData<Boolean>

    abstract val invalidPasswordLiveData: LiveData<Unit>
    abstract val invalidEmailLiveData: LiveData<Unit>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val authSuccessLiveData: LiveData<Unit>

    abstract fun setEmail(email: String)
    abstract fun setPassword(password: String)
    abstract fun setRememberMe(remember: Boolean)

    abstract fun login()
    abstract fun signUp()
}

class LoginViewModelImpl(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase,
    loginViewBehaviour: LoginViewBehaviour
) : LoginViewModel() {

    override val emailLiveData = MutableLiveData<String>()
    override val passwordLiveData = MutableLiveData<String>()
    override val rememberMeLiveData = MutableLiveData<Boolean>()

    override val invalidEmailLiveData = SingleLiveEvent<Unit>()
    override val invalidPasswordLiveData = SingleLiveEvent<Unit>()

    override val signUpEnabledLiveData = MutableLiveData(loginViewBehaviour.isSignUpEnabled)

    override val loadingLiveData = MutableLiveData<Boolean>()

    override val authSuccessLiveData = SingleLiveEvent<Unit>()

    private var email: String = ""
    private var password: String = ""
    private var rememberMe: Boolean = false

    override fun login() {
        if (!validateCredentials()) return
        performLogin()
    }

    override fun signUp() {
        //TODO login implement sign up logic
    }

    override fun setEmail(email: String) {
        this.email = email
        emailLiveData.value = email
    }

    override fun setPassword(password: String) {
        this.password = password
        passwordLiveData.value = password
    }

    override fun setRememberMe(remember: Boolean) {
        this.rememberMe = remember
        rememberMeLiveData.value = remember
    }

    private fun performLogin() {
        viewModelScope.startLoadingTask(loadingLiveData) {
            safeCall {
                loginUseCase.login(
                    email = email,
                    password = password,
                    rememberMe = rememberMe
                )
            } ?: return@startLoadingTask

            withMainContext {
                authSuccessLiveData.call()
            }
        }
    }

    private fun validateCredentials(): Boolean {
        var credentialsValid = true

        if (!isEmailValid(email)) {
            invalidEmailLiveData.call()
            credentialsValid = false
        }
        if (!isPasswordValid(password)) {
            invalidPasswordLiveData.call()
            credentialsValid = false
        }

        return credentialsValid
    }

    private fun isEmailValid(email: String): Boolean {
        return validateEmailUseCase(email)
    }

    private fun isPasswordValid(password: String): Boolean {
        return validatePasswordUseCase(password)
    }

}