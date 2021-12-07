package com.flaringapp.testingsimulator.presentation.features.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.domain.usecase.validation.*
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class SignUpVewModel : BaseViewModel() {
    abstract val emailLiveData: LiveData<String>
    abstract val firstNameLiveData: LiveData<String>
    abstract val lastNameLiveData: LiveData<String>
    abstract val studyingAtLiveData: LiveData<String>
    abstract val workPlaceLiveData: LiveData<String>
    abstract val roleLiveData: LiveData<String>
    abstract val passwordLiveData: LiveData<String>
    abstract val confirmPasswordLiveData: LiveData<String>

    abstract val invalidEmailLiveData: LiveData<Unit>
    abstract val invalidFirstNameLiveData: LiveData<Unit>
    abstract val invalidLastNameLiveData: LiveData<Unit>
    abstract val invalidPasswordLiveData: LiveData<Unit>
    abstract val passwordsNotEqualLiveData: LiveData<Unit>

    abstract fun setEmail(email: String)
    abstract fun setFirstName(firstName: String)
    abstract fun setLastName(lastName: String)
    abstract fun setStudyingAt(studyingAt: String)
    abstract fun setWorkPlace(workPlace: String)
    abstract fun setRole(role: String)
    abstract fun setPassword(password: String)
    abstract fun setConfirmPassword(confirmPassword: String)

    abstract fun signUp()
}

class SignUpVewModelImpl(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val passwordEqualityUseCase: ValidatePasswordEqualityUseCase
) : SignUpVewModel() {

    private var email: String = ""
    private var firstName: String = ""
    private var lastName: String = ""
    private var studyingAt: String = ""
    private var workPlace: String = ""
    private var role: String = ""
    private var password: String = ""
    private var confirmPassword: String = ""

    override val emailLiveData = MutableLiveData<String>()
    override val firstNameLiveData = MutableLiveData<String>()
    override val lastNameLiveData = MutableLiveData<String>()
    override val studyingAtLiveData = MutableLiveData<String>()
    override val workPlaceLiveData = MutableLiveData<String>()
    override val roleLiveData = MutableLiveData<String>()
    override val passwordLiveData = MutableLiveData<String>()
    override val confirmPasswordLiveData = MutableLiveData<String>()

    override val invalidEmailLiveData = SingleLiveEvent<Unit>()
    override val invalidFirstNameLiveData = SingleLiveEvent<Unit>()
    override val invalidLastNameLiveData = SingleLiveEvent<Unit>()
    override val invalidPasswordLiveData = SingleLiveEvent<Unit>()
    override val passwordsNotEqualLiveData = SingleLiveEvent<Unit>()

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

    override fun setStudyingAt(studyingAt: String) {
        this.studyingAt = studyingAt
        studyingAtLiveData.value = studyingAt
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
        if (!validateData()) return

        //TODO perform sign up
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