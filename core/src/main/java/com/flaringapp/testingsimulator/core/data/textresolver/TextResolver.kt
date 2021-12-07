package com.flaringapp.testingsimulator.core.data.textresolver

import android.os.Parcelable
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider

interface TextResolver : Parcelable {

    fun resolveText(textProvider: TextProvider): CharSequence

}