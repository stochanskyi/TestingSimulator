package com.flaringapp.testingsimulator.presentation.common.message

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.textWithVisibility
import com.flaringapp.testingsimulator.presentation.R
import com.flaringapp.testingsimulator.presentation.databinding.DialogMessageBinding
import com.flaringapp.testingsimulator.presentation.mvvm.BaseDialog
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.android.ext.android.inject

open class MessageDialog : BaseDialog(R.layout.dialog_message) {

    companion object {
        const val RESULT_CLOSED = "result_closed"

        private const val PARAMS_KEY = "key_params"

        @JvmStatic
        fun newInstance(
            params: MessageDialogParams
        ) = MessageDialog().apply {
            arguments = Bundle().apply {
                putParcelable(PARAMS_KEY, params)
            }
        }
    }

    private val textProvider: TextProvider by inject()

    private val binding: DialogMessageBinding by viewBinding(DialogMessageBinding::bind)

    private val params: MessageDialogParams by lazy {
        requireArguments().getParcelable(PARAMS_KEY)!!
    }

    override fun initViews() = with(binding) {
        textHeader.textWithVisibility = params.title?.resolveText(textProvider)
        textMessage.text = params.message.resolveText(textProvider)
        buttonPositive.text = params.action.resolveText(textProvider)

        buttonPositive.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        notifyClosed()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        notifyClosed()
    }

    private fun notifyClosed() {
        setFragmentResult(RESULT_CLOSED, Bundle.EMPTY)
    }
}