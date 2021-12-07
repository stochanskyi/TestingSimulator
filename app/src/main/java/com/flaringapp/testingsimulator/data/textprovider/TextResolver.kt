package com.flaringapp.testingsimulator.data.textprovider

import android.os.Parcelable
import com.flaringapp.testingsimulator.data.text.TextProvider

interface TextResolver : Parcelable {

    fun resolveText(textProvider: TextProvider): CharSequence

}