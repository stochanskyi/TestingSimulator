package com.flaringapp.testingsimulator.data.network

object NetworkConstants {

    const val API_KEY = "Authorization"

    fun formatApiKey(token: String) = "Bearer $token"

}