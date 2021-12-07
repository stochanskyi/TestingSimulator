package com.flaringapp.testingsimulator.app

import com.flaringapp.testingsimulator.data.text.TextProvider
import org.koin.core.context.GlobalContext

val sharedTextProvider: TextProvider by GlobalContext.get().inject()