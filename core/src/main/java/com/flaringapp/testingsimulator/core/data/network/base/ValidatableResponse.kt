package com.flaringapp.testingsimulator.core.data.network.base

interface ValidateableResponse<T> {
    val status: String
    val message: String?
    val data: T?
    val errorType: String?
}