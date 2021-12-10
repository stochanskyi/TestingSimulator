package com.flaringapp.testingsimulator.user.presentation.tests.testDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.isRunning
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent
import com.flaringapp.testingsimulator.core.presentation.utils.startLoadingTask
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatter
import com.flaringapp.testingsimulator.presentation.data.taxonomy.DefaultTaxonomyFormatterConfig
import com.flaringapp.testingsimulator.presentation.mvvm.BaseViewModel
import com.flaringapp.testingsimulator.user.R
import com.flaringapp.testingsimulator.user.domain.tests.GetUserTestDetailsUseCase
import com.flaringapp.testingsimulator.user.domain.tests.StartTestUseCase
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTest
import com.flaringapp.testingsimulator.user.domain.tests.models.UserTestDetails
import com.flaringapp.testingsimulator.user.presentation.tests.testDetails.models.TaskPassingNavArgs
import com.flaringapp.testingsimulator.user.presentation.tests.testDetails.models.UserTestStatusViewData
import kotlinx.coroutines.Job

abstract class UserTestDetailsViewModel : BaseViewModel() {

    abstract val loadingLiveData: LiveData<Boolean>

    abstract val testNameLiveData: LiveData<String>

    abstract val testStateLiveData: LiveData<UserTestStatusViewData>

    abstract val testStatisticsLiveData: LiveData<CharSequence>

    abstract val openTasksLiveData: LiveData<TaskPassingNavArgs>

    abstract fun init(testId: Int, name: String)

    abstract fun launchTest()

}

class UserTestDetailsViewModelImpl(
    private val getUserTestDetailsUseCase: GetUserTestDetailsUseCase,
    private val startTestUseCase: StartTestUseCase,
    private val textProvider: TextProvider,
    private val colorProvider: ColorProvider,
    private val taxonomyFormatter: TaxonomyFormatter,
) : UserTestDetailsViewModel() {

    private var testDetails: UserTestDetails? = null

    override val loadingLiveData = MutableLiveData(false)
    override val testNameLiveData = MutableLiveData("")
    override val testStateLiveData = MutableLiveData<UserTestStatusViewData>()
    override val testStatisticsLiveData = MutableLiveData<CharSequence>()
    override val openTasksLiveData = SingleLiveEvent<TaskPassingNavArgs>()

    private var startTestJob: Job? = null

    init {
        taxonomyFormatter.config = DefaultTaxonomyFormatterConfig.customize(
            colorProvider = colorProvider,
            valueTextSize = 14,
        )
    }

    override fun init(testId: Int, name: String) {
        loadTestDetails(testId)
        testNameLiveData.value = name
    }

    override fun launchTest() {
        val test = testDetails ?: return

        if (!test.isInProgress) {
            startAndOpenTest(test)
            return
        }

        openTest(test)
    }

    private fun loadTestDetails(testId: Int) {
        viewModelScope.startLoadingTask(loadingLiveData) {
            val result = safeCall { getUserTestDetailsUseCase(testId) } ?: return@startLoadingTask
            withMainContext {
                testDetails = result

                updateData(result)
            }
        }
    }

    private fun startAndOpenTest(test: UserTestDetails) {
        if (startTestJob.isRunning) return

        startTestJob = viewModelScope.startLoadingTask(loadingLiveData) {
            val firstTask = safeCall {
                startTestUseCase(test.id)
            } ?: return@startLoadingTask

            withMainContext {
                openTest(test, firstTask.id)
            }
        }
    }

    private fun openTest(test: UserTestDetails, taskId: Int? = null) {
        openTasksLiveData.value = TaskPassingNavArgs(
            testId = test.id,
            tasksCount = test.tasksCount,
            taskId = taskId,
        )
    }

    private fun updateData(testDetails: UserTestDetails) {
        testNameLiveData.value = testDetails.name
        testStateLiveData.value = testDetails.createStateViewData()

        updateStatistics(testDetails)
    }

    private fun updateStatistics(testDetails: UserTestDetails) {
        val statistics = createCustomStatistics(testDetails) + testDetails.statistics
        testStatisticsLiveData.value = taxonomyFormatter.format(statistics)
    }

    private fun createCustomStatistics(testDetails: UserTestDetails): Map<String, String> {
        val tasksCount = testDetails.tasksCount.toString()
        return mapOf(textProvider.getString(R.string.statistics_tests_count) to tasksCount)
    }

    private fun UserTest.createStateViewData(): UserTestStatusViewData {
        val status: CharSequence
        val statusColor: Int
        val buttonLabel: String
        val statusEmojiRes: Int
        when {
            isInProgress -> {
                status = textProvider.getString(R.string.task_in_progress)
                statusColor = colorProvider.getColor(R.color.task_in_progress)
                statusEmojiRes = R.drawable.img_crossed_fingers
                buttonLabel = textProvider.getString(R.string.button_continue_test)
            }
            mark == null -> {
                status = ""
                statusColor = colorProvider.transparent()
                statusEmojiRes = R.drawable.img_oncoming_fist
                buttonLabel = textProvider.getString(R.string.button_start_test)
            }
            isPassed -> {
                status = textProvider.formatPercent(mark.toString())
                statusColor = colorProvider.getColor(R.color.task_passed)
                statusEmojiRes = R.drawable.img_partuing_face
                buttonLabel = textProvider.getString(R.string.button_try_again_test)
            }
            else -> {
                status = textProvider.formatPercent(mark.toString())
                statusColor = colorProvider.getColor(R.color.task_failed)
                statusEmojiRes = R.drawable.img_pile_of_poo
                buttonLabel = textProvider.getString(R.string.button_try_again_test)
            }
        }

        return UserTestStatusViewData(
            statusName = status,
            statusEmojiRes = statusEmojiRes,
            statusColor = statusColor,
            buttonLabel = buttonLabel
        )
    }

}