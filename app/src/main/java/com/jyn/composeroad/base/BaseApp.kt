package com.jyn.composeroad.base

import android.app.Application
import com.apkfuns.logutils.LogUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtils.getLogConfig().configShowBorders(false)
    }
}