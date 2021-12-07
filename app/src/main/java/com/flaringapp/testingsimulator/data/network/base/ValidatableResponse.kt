package com.flaringapp.testingsimulator.data.network.base

interface ValidateableResponse<T> {
    val status: String
    val message: String?
    val data: T?
    val errorType: String?
}