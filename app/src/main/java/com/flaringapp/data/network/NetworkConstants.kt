package com.flaringapp.data.network

object NetworkConstants {

    const val API_KEY = "Authorization"

    fun formatApiKey(token: String) = "Bearer $token"

}