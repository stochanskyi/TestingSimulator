package com.flaringapp.testingsimulator.core.data.network.base

class ApiException(
    override val message: String?
) : RuntimeException()