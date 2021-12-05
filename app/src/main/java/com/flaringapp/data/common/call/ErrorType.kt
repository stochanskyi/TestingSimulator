package com.flaringapp.data.common.call

sealed class ErrorType {
    object Unauthenticated : ErrorType()
    object ApiResourceNotFound : ErrorType()
}