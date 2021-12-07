package com.flaringapp.testingsimulator.presentation.features.common.message

import android.os.Parcelable
import androidx.annotation.StringRes
import com.flaringapp.testingsimulator.R
import com.flaringapp.testingsimulator.data.textprovider.TextNamedResolver
import com.flaringapp.testingsimulator.data.textprovider.TextResolver
import com.flaringapp.testingsimulator.data.textprovider.TextResourceResolver
import kotlinx.parcelize.Parcelize

@Parcelize
class MessageDialogParams(
    val title: TextResolver?,
    val message: TextResolver,
    val action: TextResolver,
) : Parcelable {

    fun createDialog() = MessageDialog.newInstance(this)

    class Builder {

        companion object {
            private val DEFAULT_ACTION = TextResourceResolver(R.string.ok)
        }

        private var title: TextResolver? = null
        private var message: TextResolver? = null

        private var action: TextResolver = DEFAULT_ACTION

        fun withTitle(resolver: TextResolver) = apply {
            this.title = resolver
        }

        fun withTitle(text: CharSequence) = apply {
            this.title = TextNamedResolver(text)
        }

        fun withTitle(@StringRes textRes: Int) = apply {
            this.title = TextResourceResolver(textRes)
        }

        fun withMessage(resolver: TextResolver) = apply {
            this.message = resolver
        }

        fun withMessage(text: CharSequence) = apply {
            this.message = TextNamedResolver(text)
        }

        fun withMessage(@StringRes textRes: Int) = apply {
            this.message = TextResourceResolver(textRes)
        }

        fun withAction(@StringRes textRes: Int) = apply {
            this.action = TextResourceResolver(textRes)
        }

        fun build(): MessageDialogParams {
            require(message != null) {
                "Message dialog message is required!"
            }
            return MessageDialogParams(
                title,
                message!!,
                action,
            )
        }

        fun buildAndCreateDialog() = build().createDialog()
    }
}