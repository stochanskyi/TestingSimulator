package com.flaringapp.presentation.utils.aspectratio

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.res.use
import androidx.core.text.isDigitsOnly
import com.flaringapp.base.R

class AspectRatioFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private const val DOMINANT_WIDTH = 0
    }

    private var isAspectRatioEnabled = false

    private var mAspectRatioWidth: Int = 1
    private var mAspectRatioHeight: Int = 1

    private var isWidthDominant = true

    init {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.AspectRatioFrameLayout,
            defStyleAttr,
            defStyleRes
        ).use {
            val aspectRatioValue = it.getString(R.styleable.AspectRatioFrameLayout_frame_aspect_ratio)
                ?: return@use

            resolveAndSetupAspectRatio(aspectRatioValue)

            val aspectRatioBaseValue =
                it.getInt(R.styleable.AspectRatioFrameLayout_frame_aspect_ratio_base, DOMINANT_WIDTH)
            resolveAndSetupAspectRatioBase(aspectRatioBaseValue)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!isAspectRatioEnabled) return

        val finalWidth: Int
        val finalHeight: Int

        if (isWidthDominant) {
            finalWidth = measuredWidth
            finalHeight = finalWidth / mAspectRatioWidth * mAspectRatioHeight
        } else {
            finalHeight = measuredHeight
            finalWidth = finalHeight * mAspectRatioWidth / mAspectRatioHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }

    private fun resolveAndSetupAspectRatio(value: String) {
        val parts = value.split(":")
        if (parts.size != 2) throw IllegalStateException(
            "Unsupported aspect ratio value: $value. Should be like : '4:3'"
        )
        parts.forEach {
            if (!it.isDigitsOnly()) throw IllegalStateException(
                "Aspect ratio part '$it' is not a digit"
            )
        }
        mAspectRatioWidth = parts[0].toInt()
        mAspectRatioHeight = parts[1].toInt()

        isAspectRatioEnabled = true
    }

    private fun resolveAndSetupAspectRatioBase(value: Int) {
        isWidthDominant = value == DOMINANT_WIDTH
    }

}