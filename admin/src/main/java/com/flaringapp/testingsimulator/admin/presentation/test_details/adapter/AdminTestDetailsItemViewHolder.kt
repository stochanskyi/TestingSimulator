package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AdminTestDetailsItemViewHolder protected constructor(root: View) :
    RecyclerView.ViewHolder(root) {

    open fun release() = Unit

}