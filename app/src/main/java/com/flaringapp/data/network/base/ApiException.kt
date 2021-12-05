package com.flaringapp.data.network.base

class ApiException(
    override val message: String?
) : RuntimeException()