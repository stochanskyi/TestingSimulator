package com.flaringapp.app

import com.flaringapp.data.text.TextProvider
import org.koin.core.context.GlobalContext

val sharedTextProvider: TextProvider by GlobalContext.get().inject()