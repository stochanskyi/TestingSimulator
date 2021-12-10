package com.flaringapp.testingsimulator.presentation.features.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.launchOnIO
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.auth.GetLastEmailUseCase
import com.flaringapp.testingsimulator.domain.features.auth.IsLoggedInUseCase
import com.flaringapp.testingsimulator.domain.features.auth.LoginUseCase
import com.flaringapp.testingsimulator.domain.usecase.validation.ValidateEmailUseCase
import com.flaringapp.testingsimulator.domain.usecase.validation.ValidatePasswordEmptyUseCase
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job

abstract class LoginViewModel : BaseViewModel() {

    abstract val signUpEnabledLiveData: LiveData<Boolean>

    abstract val emailLiveData: LiveData<String>
    abstract val passwordLiveData: LiveData<String>
    abstract val rememberMeLiveData: LiveData<Boolean>

    abstract val updateEmailLiveData: LiveData<Unit>

    abstract val invalidEmailLiveData: LiveData<Unit>
    abstract val passwordEmptyLiveData: LiveData<Unit>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val openSignUpLiveData: LiveData<Unit>

    abstract val authSuccessLiveData: LiveData<Unit>

    abstract fun setEmail(email: String)
    abstract fun setPassword(password: String)
    abstract fun setRememberMe(remember: Boolean)

    abstract fun login()
    abstract fun signUp()
}

class LoginViewModelImpl(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val getLastEmailUseCase: GetLastEmailUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordEmptyUseCase: ValidatePasswordEmptyUseCase,
    private val loginUseCase: LoginUseCase,
    loginViewBehaviour: LoginViewBehaviour
) : LoginViewModel() {

    override val signUpEnabledLiveData = MutableLiveData(loginViewBehaviour.isSignUpEnabled)

    override val emailLiveData = MutableLiveData("")
    override val passwordLiveData = MutableLiveData("")
    override val rememberMeLiveData = MutableLiveData(true)

    override val updateEmailLiveData = SingleLiveEvent<Unit>()

    override val invalidEmailLiveData = SingleLiveEvent<Unit>()
    override val passwordEmptyLiveData = SingleLiveEvent<Unit>()

    override val loadingLiveData = MutableLiveData(false)

    override val openSignUpLiveData = SingleLiveEvent<Unit>()

    override val authSuccessLiveData = SingleLiveEvent<Unit>()

    private var email: String = ""
    private var password: String = ""
    private var rememberMe: Boolean = false

    private var loginJob: Job? = null

    init {
        initData()
    }

    override fun login() {
        if (!validateCredentials()) return
        performLogin()
    }

    override fun signUp() {
        openSignUpLiveData.call()
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

    private fun initData() {
        if (isLoggedInUseCase()) {
            authSuccessLiveData.call()
            return
        }

        viewModelScope.launchOnIO {
            val email = safeCall {
                getLastEmailUseCase()
            } ?: return@launchOnIO

            withMainContext {
                setEmail(email)
                updateEmailLiveData.call()
            }
        }
    }

    private fun performLogin() {
        if (loginJob.isRunning) return
        loginJob = viewModelScope.startLoadingTask(loadingLiveData) {
            safeCall {
                loginUseCase(
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
        if (!isPasswordNotEmpty(password)) {
            passwordEmptyLiveData.call()
            credentialsValid = false
        }

        return credentialsValid
    }

    private fun isEmailValid(email: String): Boolean {
        return validateEmailUseCase(email)
    }

    private fun isPasswordNotEmpty(password: String): Boolean {
        return validatePasswordEmptyUseCase(password)
    }

}