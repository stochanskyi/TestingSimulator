package com.flaringapp.testingsimulator.core.presentation.views.bettercheckbox

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox

/**
 * Features:
 * - Check/toggle without notifying check change listener
 */
class BetterCheckBox : AppCompatCheckBox {

    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        this.onCheckedChangeListener = listener
        super.setOnCheckedChangeListener(listener)
    }

    fun toggle(notifyListener: Boolean = true) {
        listenerSafeAction(notifyListener) {
            toggle()
        }
    }

    fun setChecked(isChecked: Boolean, notifyListener: Boolean = true) {
        listenerSafeAction(notifyListener) {
            setChecked(isChecked)
        }
    }

    private fun listenerSafeAction(notifyListener: Boolean, action: () -> Unit) {
        if (notifyListener) {
            action()
        } else {
            actionWithoutListener { action() }
        }
    }

    private fun actionWithoutListener(action: () -> Unit) {
        super.setOnCheckedChangeListener(null)
        action()
        super.setOnCheckedChangeListener(onCheckedChangeListener)
    }
}