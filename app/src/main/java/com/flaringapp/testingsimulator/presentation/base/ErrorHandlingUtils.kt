package com.flaringapp.testingsimulator.presentation.base

import android.util.Log
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.flaringapp.testingsimulator.R
import com.flaringapp.testingsimulator.presentation.features.common.message.MessageDialogParams
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

const val ERROR_DIALOG = "dialog_error"
const val ERROR_NETWORK_DIALOG = "${ERROR_DIALOG}_network"
const val ERROR_ACCESS_DIALOG = "${ERROR_DIALOG}_access"
const val ERROR_UNKNOWN_DIALOG = "${ERROR_DIALOG}_unknown"

// Error processing

fun Throwable.isInternetError(): Boolean {
    return this is UnknownHostException || this is SocketTimeoutException
}

fun Throwable.isServerUnavailableError(): Boolean {
    return this is ConnectException
}

fun Fragment.handleErrorInternal(
    error: Throwable,
    tag: String? = null
) {
    childFragmentManager.handleErrorInternal(error, tag)
}

fun FragmentManager.handleErrorInternal(
    error: Throwable,
    tag: String? = null
) {
    if (ignoreException(error)) return

    if (error.isInternetError()) {
        showInternetErrorDialog(tag)
        return
    }

    Log.e("ErrorHandler", error.stackTraceToString())

    if (error.isServerUnavailableError()) {
        showServerConnectionErrorDialog(tag)
        return
    }

    when (error) {
        is HttpException -> handleHttpError(error, tag)
        else -> {
            val message = error.message
            if (message.isNullOrEmpty()) {
                showUnknownErrorDialog(tag)
                return
            }

            error.message?.let {
                showErrorDialog(
                    message = it,
                    tag = tag ?: ERROR_DIALOG
                )
            }
        }
    }
}

private fun FragmentManager.handleHttpError(
    error: HttpException,
    tag: String?
) {
    when (error.code()) {
        403 -> showAccessErrorDialog(tag)
        else -> showUnknownErrorDialog(tag)
    }
}

private fun ignoreException(error: Throwable): Boolean {
    return error.let {
        it is SSLHandshakeException
    }
}

//Case Extensions

private fun FragmentManager.showInternetErrorDialog(
    tag: String? = ERROR_NETWORK_DIALOG
) {
    showErrorDialog(
        messageRes = R.string.error_no_internet_connection,
        tag = tag
    )
}

private fun FragmentManager.showServerConnectionErrorDialog(
    tag: String? = ERROR_NETWORK_DIALOG
) {
    showErrorDialog(
        messageRes = R.string.error_server_connection,
        tag = tag
    )
}

private fun FragmentManager.showAccessErrorDialog(
    tag: String? = ERROR_ACCESS_DIALOG
) {
    showErrorDialog(
        messageRes = R.string.error_server_access,
        tag = tag
    )
}

private fun FragmentManager.showUnknownErrorDialog(
    tag: String? = ERROR_UNKNOWN_DIALOG
) {
    showErrorDialog(
        messageRes = R.string.error_unknown,
        tag = tag
    )
}

private fun FragmentManager.showErrorDialog(
    @StringRes headerRes: Int? = null,
    @StringRes messageRes: Int,
    tag: String?
) {
    if (shouldIgnoreDialog(tag)) return
    MessageDialogParams.Builder()
        .apply { headerRes?.let { withTitle(it) } }
        .withMessage(messageRes)
        .buildAndCreateDialog()
        .show(this, tag)
}

private fun FragmentManager.showErrorDialog(
    header: CharSequence? = null,
    message: CharSequence,
    tag: String?
) {
    MessageDialogParams.Builder()
        .apply { header?.let { withTitle(it) } }
        .withMessage(message)
        .buildAndCreateDialog()
        .show(this, tag)
}

private fun FragmentManager.shouldIgnoreDialog(tag: String?): Boolean {
    if (tag == null) return false
    return findFragmentByTag(tag) != null
}