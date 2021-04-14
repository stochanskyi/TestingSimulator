package com.flaringapp.app.di

import com.flaringapp.data.network.RetrofitAdapter
import org.koin.dsl.module

val NetworkModule = module {

    val adapter: RetrofitAdapter by lazy { RetrofitAdapter() }

}