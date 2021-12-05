package com.flaringapp.presentation.base

import androidx.lifecycle.LiveData

interface BaseViewModelContract {

    val errorData: LiveData<Throwable>

}