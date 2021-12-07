package com.flaringapp.testingsimulator.data.network.base

class ApiException(
    override val message: String?
) : RuntimeException()