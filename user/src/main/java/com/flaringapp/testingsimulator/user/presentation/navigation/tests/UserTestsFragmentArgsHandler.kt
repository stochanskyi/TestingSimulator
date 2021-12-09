package com.flaringapp.testingsimulator.user.presentation.navigation.tests

import android.os.Bundle
import com.flaringapp.testingsimulator.presentation.features.tests.TestsFragmentArgs
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TestsFragmentArgsHandler
import com.flaringapp.testingsimulator.presentation.features.tests.adapter.args.TopicPreliminaryData

class UserTestsFragmentArgsHandler : TestsFragmentArgsHandler {

    private var params: TestsFragmentArgs? = null

    override fun handleParams(bundle: Bundle) {
        params = TestsFragmentArgs.fromBundle(bundle)
    }

    override val topicPreliminaryData: TopicPreliminaryData?
        get() = params?.topicData

}