package com.flaringapp.testingsimulator.presentation.base

import androidx.lifecycle.LiveData

interface BaseViewModelContract {

    val errorData: LiveData<Throwable>

}