package com.flaringapp.testingsimulator.presentation.features.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.usecase.validation.*
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.Job

abstract class SignUpVewModel : BaseViewModel() {

    abstract val isStudyingEnabled: LiveData<Boolean>
    abstract val isWorkPlaceEnabled: LiveData<Boolean>
    abstract val isRoleEnabled: LiveData<Boolean>

    abstract val emailLiveData: LiveData<String>
    abstract val firstNameLiveData: LiveData<String>
    abstract val lastNameLiveData: LiveData<String>
    abstract val studyingLiveData: LiveData<String>
    abstract val workPlaceLiveData: LiveData<String>
    abstract val roleLiveData: LiveData<String>
    abstract val passwordLiveData: LiveData<String>
    abstract val confirmPasswordLiveData: LiveData<String>

    abstract val invalidEmailLiveData: LiveData<Unit>
    abstract val invalidFirstNameLiveData: LiveData<Unit>
    abstract val invalidLastNameLiveData: LiveData<Unit>
    abstract val invalidPasswordLiveData: LiveData<Unit>
    abstract val passwordsNotEqualLiveData: LiveData<Unit>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val authSuccessLiveData: LiveData<Unit>

    abstract fun setEmail(email: String)
    abstract fun setFirstName(firstName: String)
    abstract fun setLastName(lastName: String)
    abstract fun setStudying(studying: String)
    abstract fun setWorkPlace(workPlace: String)
    abstract fun setRole(role: String)
    abstract fun setPassword(password: String)
    abstract fun setConfirmPassword(confirmPassword: String)

    abstract fun signUp()
}

class SignUpVewModelImpl(
    private val behaviour: SignUpViewBehaviour,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val passwordEqualityUseCase: ValidatePasswordEqualityUseCase,
) : SignUpVewModel() {

    private var email: String = ""
    private var firstName: String = ""
    private var lastName: String = ""
    private var studying: String = ""
    private var workPlace: String = ""
    private var role: String = ""
    private var password: String = ""
    private var confirmPassword: String = ""

    override val isStudyingEnabled = MutableLiveData(behaviour.isStudyingEnabled)
    override val isWorkPlaceEnabled = MutableLiveData(behaviour.isWorkPlaceEnabled)
    override val isRoleEnabled: LiveData<Boolean> = MutableLiveData(behaviour.isRoleEnabled)

    override val emailLiveData = MutableLiveData(email)
    override val firstNameLiveData = MutableLiveData(firstName)
    override val lastNameLiveData = MutableLiveData(lastName)
    override val studyingLiveData = MutableLiveData(studying)
    override val workPlaceLiveData = MutableLiveData(workPlace)
    override val roleLiveData = MutableLiveData(role)
    override val passwordLiveData = MutableLiveData(password)
    override val confirmPasswordLiveData = MutableLiveData(confirmPassword)

    override val invalidEmailLiveData = SingleLiveEvent<Unit>()
    override val invalidFirstNameLiveData = SingleLiveEvent<Unit>()
    override val invalidLastNameLiveData = SingleLiveEvent<Unit>()
    override val invalidPasswordLiveData = SingleLiveEvent<Unit>()
    override val passwordsNotEqualLiveData = SingleLiveEvent<Unit>()

    override val loadingLiveData = MutableLiveData(false)

    override val authSuccessLiveData = SingleLiveEvent<Unit>()

    private var signUpJob: Job? = null

    override fun setEmail(email: String) {
        this.email = email
        emailLiveData.value = email
    }

    override fun setFirstName(firstName: String) {
        this.firstName = firstName
        firstNameLiveData.value = firstName
    }

    override fun setLastName(lastName: String) {
        this.lastName = lastName
        lastNameLiveData.value = lastName
    }

    override fun setStudying(studying: String) {
        this.studying = studying
        studyingLiveData.value = studying
    }

    override fun setWorkPlace(workPlace: String) {
        this.workPlace = workPlace
        workPlaceLiveData.value = workPlace
    }

    override fun setRole(role: String) {
        this.role = role
        roleLiveData.value = role
    }

    override fun setPassword(password: String) {
        this.password = password
        passwordLiveData.value = password
    }

    override fun setConfirmPassword(confirmPassword: String) {
        this.confirmPassword = confirmPassword
        confirmPasswordLiveData.value = confirmPassword
    }

    override fun signUp() {
        if (signUpJob.isRunning) return
        if (!validateData()) return

        signUpJob = viewModelScope.startLoadingTask(loadingLiveData) {
            safeCall {
                behaviour.createAccount(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    studying = studying,
                    workPlace = workPlace,
                    role = role,
                    password = password,
                    repeatPassword = confirmPassword
                )
            } ?: return@startLoadingTask

            withMainContext {
                authSuccessLiveData.call()
            }
        }
    }

    private fun validateData(): Boolean {
        var isDataValid = true

        if (!validateEmailUseCase(email)) {
            invalidEmailLiveData.call()
            isDataValid = false
        }
        if (!validatePasswordUseCase(password)) {
            invalidPasswordLiveData.call()
            isDataValid = false
        }
        if (!validateFirstNameUseCase(firstName)) {
            invalidFirstNameLiveData.call()
            isDataValid = false
        }
        if (!validateLastNameUseCase(lastName)) {
            invalidLastNameLiveData.call()
            isDataValid = false
        }
        if (!passwordEqualityUseCase(password, confirmPassword)) {
            passwordsNotEqualLiveData.call()
        }

        return isDataValid
    }
}