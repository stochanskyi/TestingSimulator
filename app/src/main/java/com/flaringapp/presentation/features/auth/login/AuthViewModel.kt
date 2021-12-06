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

    abstract val authSuccessLiveData: LiveData<Unit>

    abstract val loginLoadingLiveData: LiveData<Boolean>

    abstract fun login()
    abstract fun signUp()
    abstract fun setEmail(email: String)
    abstract fun setPassword(password: String)
    abstract fun setRememberMe(remember: Boolean)
}

class AuthViewModelImpl(
    private val loginUseCase: LoginUseCase
) : AuthViewModel() {

    override val invalidPasswordLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

    override val invalidEmailLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

    override val authSuccessLiveData: SingleLiveEvent<Unit> = SingleLiveEvent()

    override val loginLoadingLiveData: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private var email: String = ""
    private var password: String = ""

    private var rememberMe: Boolean = false

    override fun login() {
        if (!validateCredentials()) return

        viewModelScope.startLoadingTask(loginLoadingLiveData) {
            safeCall { loginUseCase.login(email, password, rememberMe) } ?: return@startLoadingTask

            withMainContext { authSuccessLiveData.call() }
        }
    }

    override fun signUp() {
        //TODO
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
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }


}