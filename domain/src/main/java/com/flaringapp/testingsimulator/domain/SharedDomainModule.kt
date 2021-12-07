package com.flaringapp.testingsimulator.domain

import com.flaringapp.testingsimulator.domain.validation.Validator
import com.flaringapp.testingsimulator.domain.validation.ValidatorImpl
import org.koin.dsl.module

val SharedDomainModule = module {

    factory<Validator> { ValidatorImpl() }

}