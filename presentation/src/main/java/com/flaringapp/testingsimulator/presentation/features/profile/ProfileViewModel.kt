package com.flaringapp.testingsimulator.presentation.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.launchOnIO
import com.flaringapp.testingsimulator.core.app.common.takeIfNotEmpty
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.LiveDataList
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.MutableLiveDataList
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiColorsProvider
import com.flaringapp.testingsimulator.domain.features.emoji.EmojiProvider
import com.flaringapp.testingsimulator.domain.features.profile.ProfileStatistics
import com.flaringapp.testingsimulator.domain.features.profile_statistics.GetProfileStatisticsUseCase
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatter
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.data.taxonomy.DefaultTaxonomyFormatterConfig
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviour
import com.flaringapp.testingsimulator.presentation.features.profile.behaviour.ProfileBehaviourGetProfileConsumer
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

abstract class ProfileViewModel : BaseViewModel() {

    abstract val nameLiveData: LiveData<String>
    abstract val emailLiveData: LiveData<CharSequence>
    abstract val studyingLiveData: LiveData<CharSequence?>
    abstract val workPlaceLiveData: LiveData<CharSequence?>
    abstract val roleLiveData: LiveData<CharSequence?>

    abstract val statisticsLiveData: LiveDataList<ProfileStatisticsViewData>

    abstract fun refreshData()
}

class ProfileViewModelImpl(
    private val behaviour: ProfileBehaviour,
    private val getStatisticsUseCase: GetProfileStatisticsUseCase,
    private val emojiProvider: EmojiProvider,
    private val emojiColorsProvider: EmojiColorsProvider,
    private val colorProvider: ColorProvider,
    private val taxonomyFormatter: TaxonomyFormatter,
) : ProfileViewModel(), ProfileBehaviourGetProfileConsumer {

    override val nameLiveData = MutableLiveData<String>()
    override val emailLiveData = MutableLiveData<CharSequence>()
    override val studyingLiveData = MutableLiveData<CharSequence?>(null)
    override val workPlaceLiveData = MutableLiveData<CharSequence?>(null)
    override val roleLiveData = MutableLiveData<CharSequence?>(null)
    override val statisticsLiveData = MutableLiveDataList<ProfileStatisticsViewData>(emptyList())

    override fun refreshData() {
        loadProfile()
    }

    init {
        taxonomyFormatter.config = DefaultTaxonomyFormatterConfig.customize(
            colorProvider = colorProvider,
            valueTextSize = 14,
        )

        loadProfile()
    }

    override fun handleProfileData(
        name: String,
        email: String,
        studying: String?,
        workPlace: String?,
        role: String?
    ) {
        nameLiveData.value = name
        emailLiveData.value = taxonomyFormatter.format(R.string.profile_email, email)
        studyingLiveData.value = studying?.takeIfNotEmpty()?.let {
            taxonomyFormatter.format(R.string.profile_studying, it)
        }
        workPlaceLiveData.value = workPlace?.takeIfNotEmpty()?.let {
            taxonomyFormatter.format(R.string.profile_work_place, it)
        }
        roleLiveData.value = role?.takeIfNotEmpty()?.let {
            taxonomyFormatter.format(R.string.profile_role, role)
        }
    }

    private fun loadProfile() {
        viewModelScope.launchOnIO {
            listOf(
                async { loadProfileData() },
                async { loadStatistics() }
            ).awaitAll()
        }
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