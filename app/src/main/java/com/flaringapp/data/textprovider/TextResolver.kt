package com.flaringapp.data.textprovider

import android.os.Parcelable
import com.flaringapp.data.text.TextProvider

interface TextResolver : Parcelable {

    fun resolveText(textProvider: TextProvider): CharSequence

}