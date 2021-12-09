package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AdminTestItemViewHolder protected constructor(root: View) :
    RecyclerView.ViewHolder(root) {

    open fun release() = Unit

}