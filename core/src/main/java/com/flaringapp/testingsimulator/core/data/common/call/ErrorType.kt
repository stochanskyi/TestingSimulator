package com.flaringapp.testingsimulator.core.data.common.call

sealed class ErrorType {
    object Unauthenticated : ErrorType()
    object ApiResourceNotFound : ErrorType()
}