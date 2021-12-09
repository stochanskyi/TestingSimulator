package com.flaringapp.testingsimulator.presentation.features.edit_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.usecase.validation.ValidateFirstNameUseCase
import com.flaringapp.testingsimulator.domain.usecase.validation.ValidateLastNameUseCase
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class EditProfileViewModel : BaseViewModel() {

    abstract val isStudyingAtEnabled: LiveData<Boolean>
    abstract val isWorkPlaceEnabled: LiveData<Boolean>
    abstract val isRoleEnabled: LiveData<Boolean>

    abstract val firstNameLiveData: LiveData<String>
    abstract val lastNameLiveData: LiveData<String>
    abstract val studyingAtLiveData: LiveData<String>
    abstract val workPlaceLiveData: LiveData<String>
    abstract val roleLiveData: LiveData<String>

    abstract val invalidFirstNameLiveData: LiveData<Unit>
    abstract val invalidLastNameLiveData: LiveData<Unit>

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val editSuccessLiveData: LiveData<Unit>

    abstract fun setFirstName(firstName: String)
    abstract fun setLastName(lastName: String)
    abstract fun setStudyingAt(studyingAt: String)
    abstract fun setWorkPlace(workPlace: String)
    abstract fun setRole(role: String)

    abstract fun save()

}

class EditProfileViewModelImpl(
    private val behaviour: EditProfileBehaviour,
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
) : EditProfileViewModel() {

    private var firstName: String = ""
    private var lastName: String = ""
    private var studyingAt: String = ""
    private var workPlace: String = ""
    private var role: String = ""

    override val isStudyingAtEnabled = MutableLiveData(behaviour.isStudyingEnabled)
    override val isWorkPlaceEnabled = MutableLiveData(behaviour.isWorkPlaceEnabled)
    override val isRoleEnabled: LiveData<Boolean> = MutableLiveData(behaviour.isRoleEnabled)

    override val firstNameLiveData = MutableLiveData(firstName)
    override val lastNameLiveData = MutableLiveData(lastName)
    override val studyingAtLiveData = MutableLiveData(studyingAt)
    override val workPlaceLiveData = MutableLiveData(workPlace)
    override val roleLiveData = MutableLiveData(role)

    override val invalidFirstNameLiveData = SingleLiveEvent<Unit>()
    override val invalidLastNameLiveData = SingleLiveEvent<Unit>()

    override val loadingLiveData = MutableLiveData<Boolean>()

    override val editSuccessLiveData = SingleLiveEvent<Unit>()

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

    override fun save() {
        if (!validateData()) return

        viewModelScope.startLoadingTask(loadingLiveData) {
            safeCall {
                behaviour.editProfile(
                    firstName = firstName,
                    lastName = lastName,
                    studying = studyingAt,
                    workPlace = workPlace,
                    role = role,
                )
            } ?: return@startLoadingTask

            withMainContext {
                editSuccessLiveData.call()
            }
        }
    }

    private fun validateData(): Boolean {
        var isDataValid = true

        if (!validateFirstNameUseCase(firstName)) {
            invalidFirstNameLiveData.call()
            isDataValid = false
        }
        if (!validateLastNameUseCase(lastName)) {
            invalidLastNameLiveData.call()
            isDataValid = false
        }

        return isDataValid
    }
}