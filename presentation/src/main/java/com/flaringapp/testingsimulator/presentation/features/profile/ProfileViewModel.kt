package com.flaringapp.testingsimulator.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class ProfileViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>
    abstract val emailLiveData: LiveData<String>
    abstract val studyingAtLiveData: LiveData<String>
    abstract val workPlaceLiveData: LiveData<String>
    abstract val roleLiveData: LiveData<String>

    abstract val statisticsLiveData: LiveDataList<ProfileStatisticsViewData>
}

class ProfileViewModelImpl : ProfileViewModel() {

    override val nameLiveData = MutableLiveData<String>()
    override val emailLiveData = MutableLiveData<String>()
    override val studyingAtLiveData = MutableLiveData<String>()
    override val workPlaceLiveData = MutableLiveData<String>()
    override val roleLiveData = MutableLiveData<String>()
    override val statisticsLiveData = MutableLiveDataList<ProfileStatisticsViewData>()

}