package com.flaringapp.testingsimulator.presentation.features.topics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.topics.GetTopicsUseCase
import com.flaringapp.testingsimulator.domain.features.topics.models.Topic
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.features.topics.models.TopicViewData
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel

abstract class TopicsViewModel : BaseViewModel() {

    abstract val loadingLiveData: LiveData<Boolean>
    abstract val topicsLiveData: LiveData<List<TopicViewData>>
    abstract val openProfileLiveData: LiveData<Unit>

    abstract fun openTopic(topicId: Int)
    abstract fun openProfile()
}

class TopicsViewModelImpl(
    private val getTopicsUseCase: GetTopicsUseCase,
) : TopicsViewModel() {

    override val loadingLiveData = MutableLiveData<Boolean>()
    override val topicsLiveData = MutableLiveData<List<TopicViewData>>()
    override val openProfileLiveData = SingleLiveEvent<Unit>()

    init {
        viewModelScope.startLoadingTask(loadingLiveData) {
            val topics = safeCall { getTopicsUseCase() } ?: return@startLoadingTask
            withMainContext {
                topicsLiveData.value = topics.map { it.toViewData() }
            }
        }
    }

    override fun openTopic(topicId: Int) {
        //TODO navigate to topics
    }

    override fun openProfile() {
        openProfileLiveData.call()
    }

    private fun Topic.toViewData() = TopicViewData(
        id = id,
        name = name,
        //TODO replace with provider
        emojiRes = R.drawable.ic_emoji_testing_enabled,
        isEnabled = enabled
    )

}