package com.flaringapp.testingsimulator.presentation.mvvm

import androidx.lifecycle.LiveData

interface BaseViewModelContract {

    val errorData: LiveData<Throwable?>

}