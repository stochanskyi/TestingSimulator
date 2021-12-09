package com.flaringapp.testingsimulator.presentation.features.tests.adapter.args

import android.os.Bundle

interface TestsFragmentArgsHandler {

    fun handleParams(bundle: Bundle)

    val topicPreliminaryData: TopicPreliminaryData?

}