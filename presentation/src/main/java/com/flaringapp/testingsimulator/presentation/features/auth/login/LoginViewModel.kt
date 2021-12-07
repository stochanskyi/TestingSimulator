package com.flaringapp.testingsimulator.presentation.features.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class LoginViewModel : BaseViewModel() {

    abstract val emailLiveData: LiveData<String>
    abstract val passwordLiveData: LiveData<String>
    abstract val rememberMeLiveData: LiveData<Boolean>

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
    private val loginUseCase: LoginUseCase,
) : LoginViewModel() {

    override val emailLiveData: MutableLiveData<String> = MutableLiveData()
    override val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    override val rememberMeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    override val invalidEmailLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()
    override val invalidPasswordLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

    override val loadingLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()

    override val authSuccessLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

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
        // TODO login move out to validator
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        // TODO login move out to validator
        return password.isNotEmpty()
    }

}