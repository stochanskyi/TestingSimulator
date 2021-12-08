package com.flaringapp.testingsimulator.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.common.call.transformList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.liveDataIO
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics
import com.flaringapp.testingsimulator.domain.features.profile_statistics.GetProfileStatisticsUseCase
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class ProfileViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>
    abstract val emailLiveData: LiveData<String>
    abstract val studyingLiveData: LiveData<String>
    abstract val workPlaceLiveData: LiveData<String>
    abstract val roleLiveData: LiveData<String>

    abstract val statisticsLiveData: LiveDataList<ProfileStatisticsViewData>
}

class ProfileViewModelImpl(
    private val getStatisticsUseCase: GetProfileStatisticsUseCase,
    private val emojiProvider: EmojiProvider,
    private val colorProvider: ColorProvider,
) : ProfileViewModel() {

    override val nameLiveData = MutableLiveData<String>()
    override val emailLiveData = MutableLiveData<String>()
    override val studyingLiveData = MutableLiveData<String>()
    override val workPlaceLiveData = MutableLiveData<String>()
    override val roleLiveData = MutableLiveData<String>()
    override val statisticsLiveData = liveDataIO<List<ProfileStatisticsViewData>> {
        loadStatistics()
    }

    private suspend fun loadStatistics() {
        val statistics = getStatisticsUseCase()
        statistics.transformList { toViewData() }
    }

    // TODO finish appearance
    private fun ProfileStatistics.toViewData(): ProfileStatisticsViewData {
        val emojiRes = emojiProvider.getEmojiOrDefault(emojiId)

        return ProfileStatisticsViewData(
            emojiRes = emojiRes,
            value = value,
            label = label,
            backgroundColor = colorProvider.getColor(R.color.statistics_brain_bg),
            valueColor = colorProvider.getColor(R.color.statistics_brain_accent),
            labelColor = colorProvider.getColor(R.color.statistics_brain_variant),
        )
    }
}