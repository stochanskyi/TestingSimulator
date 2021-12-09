package com.flaringapp.testingsimulator.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.launchOnIO
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsProvider
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics
import com.flaringapp.testingsimulator.domain.features.profile_statistics.GetProfileStatisticsUseCase
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviourGetProfileConsumer
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

abstract class ProfileViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>
    abstract val emailLiveData: LiveData<String>
    abstract val studyingLiveData: LiveData<String?>
    abstract val workPlaceLiveData: LiveData<String?>
    abstract val roleLiveData: LiveData<String?>

    abstract val statisticsLiveData: LiveDataList<ProfileStatisticsViewData>
}

class ProfileViewModelImpl(
    private val behaviour: ProfileBehaviour,
    private val getStatisticsUseCase: GetProfileStatisticsUseCase,
    private val emojiProvider: EmojiProvider,
    private val emojiColorsProvider: EmojiColorsProvider,
    private val colorProvider: ColorProvider,
) : ProfileViewModel(), ProfileBehaviourGetProfileConsumer {

    override val nameLiveData = MutableLiveData<String>()
    override val emailLiveData = MutableLiveData<String>()
    override val studyingLiveData = MutableLiveData<String?>(null)
    override val workPlaceLiveData = MutableLiveData<String?>(null)
    override val roleLiveData = MutableLiveData<String?>(null)
    override val statisticsLiveData = MutableLiveDataList<ProfileStatisticsViewData>(emptyList())

    init {
        viewModelScope.launchOnIO {
            listOf(
                async { loadProfileData() },
                async { loadStatistics() }
            ).awaitAll()
        }
    }

    override fun handleProfileData(
        name: String,
        email: String,
        studying: String?,
        workPlace: String?,
        role: String?
    ) {
        nameLiveData.value = name
        emailLiveData.value = email
        studyingLiveData.value = studying
        workPlaceLiveData.value = workPlace
        roleLiveData.value = role
    }

    private suspend fun loadProfileData() {
        safeCall {
            behaviour.loadProfile(this@ProfileViewModelImpl)
        }
    }

    private suspend fun loadStatistics() {
        val statistics = safeCall {
            getStatisticsUseCase()
        } ?: return

        val viewData = statistics.map { it.toViewData() }

        withMainContext {
            statisticsLiveData.value = viewData
        }
    }

    private fun ProfileStatistics.toViewData(): ProfileStatisticsViewData {
        val emojiRes = emojiProvider.getEmojiOrDefault(emojiId)
        val emojiColorsRes = emojiColorsProvider.getEmojiColorsOrDefault(emojiRes)

        return ProfileStatisticsViewData(
            emojiRes = emojiRes,
            value = value,
            label = label,
            backgroundColor = colorProvider.getColor(emojiColorsRes.background),
            valueColor = colorProvider.getColor(emojiColorsRes.accent),
            labelColor = colorProvider.getColor(emojiColorsRes.variant),
        )
    }
}