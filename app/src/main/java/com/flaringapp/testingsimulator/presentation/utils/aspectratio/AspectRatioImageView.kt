package com.flaringapp.testingsimulator.presentation.utils.aspectratio

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import androidx.core.text.isDigitsOnly
import com.flaringapp.testingsimulator.R
import com.flaringapp.testingsimulator.presentation.utils.aspectratio.AspectRatioResolvers.DOMINANT_AUTO_RESOLVER
import com.flaringapp.testingsimulator.presentation.utils.aspectratio.AspectRatioResolvers.DOMINANT_HEIGHT_RESOLVER
import com.flaringapp.testingsimulator.presentation.utils.aspectratio.AspectRatioResolvers.DOMINANT_WIDTH_RESOLVER

class AspectRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        const val DOMINANT_WIDTH = 0
        const val DOMINANT_HEIGHT = 1
        const val DOMINANT_AUTO = 2
    }

    private var isAspectRatioEnabled = false

    var aspectRatioWidth: Int = 1
        private set
    var aspectRatioHeight: Int = 1
        private set

    private var sizeResolver: SizeResolver = DOMINANT_AUTO_RESOLVER

    init {
        initAttrs(attrs, defStyleAttr)

        updateSizeResolverAspectRatio()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!isAspectRatioEnabled) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }

        val size = sizeResolver.resolve(widthMeasureSpec, heightMeasureSpec)

        val finalWidthSpec = MeasureSpec.makeMeasureSpec(size.width, MeasureSpec.EXACTLY)
        val finalHeightSpec = MeasureSpec.makeMeasureSpec(size.height, MeasureSpec.EXACTLY)

        super.onMeasure(finalWidthSpec, finalHeightSpec)
    }

    fun setAspectRatio(width: Int, height: Int) {
        aspectRatioWidth = width
        aspectRatioHeight = height

        isAspectRatioEnabled = true

        updateSizeResolverAspectRatio()
    }

    private fun updateSizeResolverAspectRatio() {
        sizeResolver.aspectRatioWidth = aspectRatioWidth
        sizeResolver.aspectRatioHeight = aspectRatioHeight
    }

    private fun initAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        context.obtainStyledAttributes(
            attrs,
            R.styleable.AspectRatioImageView,
            defStyleAttr,
            0
        ).use {
            val aspectRatioValue =
                it.getString(R.styleable.AspectRatioImageView_iv_aspect_ratio)
                    ?: return@use

            resolveAndSetupAspectRatio(aspectRatioValue)

            val aspectRatioBaseValue =
                it.getInt(
                    R.styleable.AspectRatioImageView_iv_aspect_ratio_base,
                    DOMINANT_WIDTH
                )
            resolveAndSetupAspectRatioBase(aspectRatioBaseValue)
        }
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
        aspectRatioWidth = parts[0].toInt()
        aspectRatioHeight = parts[1].toInt()

        isAspectRatioEnabled = true
    }

    private fun resolveAndSetupAspectRatioBase(value: Int) {
        sizeResolver = when (value) {
            DOMINANT_WIDTH -> DOMINANT_WIDTH_RESOLVER
            DOMINANT_HEIGHT -> DOMINANT_HEIGHT_RESOLVER
            DOMINANT_AUTO -> DOMINANT_AUTO_RESOLVER
            else -> throw IllegalStateException("Unsupported aspect ratio base type $value")
        }
    }

}