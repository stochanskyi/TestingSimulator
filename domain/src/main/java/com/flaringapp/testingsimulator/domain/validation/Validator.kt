package com.flaringapp.testingsimulator.domain.validation

import java.util.regex.Pattern

interface Validator {

    fun validateEmail(email: String): Boolean

    fun validatePassword(password: String): Boolean

    fun validateFirstName(firstName: String): Boolean
    fun validateLastName(lastName: String): Boolean

}

class ValidatorImpl : Validator {

    override fun validateEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= PASSWORD_MIN_SIZE
    }

    override fun validateFirstName(firstName: String): Boolean {
        return firstName.trim().isNotEmpty()
    }

    override fun validateLastName(lastName: String): Boolean {
        return lastName.trim().isNotEmpty()
    }

    companion object {
        private const val PASSWORD_MIN_SIZE = 8

        private val EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
        )
    }
}