package com.flaringapp.presentation.features.auth.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.app.common.withMainContext
import com.flaringapp.data.usecase.auth.LoginUseCase
import com.flaringapp.presentation.base.BaseViewModel
import com.flaringapp.presentation.utils.common.SingleLiveEvent
import com.flaringapp.presentation.utils.startLoadingTask

abstract class AuthViewModel : BaseViewModel() {

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

class AuthViewModelImpl(
    private val loginUseCase: LoginUseCase,
) : AuthViewModel() {

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
    }

    override fun setPassword(password: String) {
        this.password = password
    }

    override fun setRememberMe(remember: Boolean) {
        this.rememberMe = remember
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