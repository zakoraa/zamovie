package com.raflis.zamovie

import android.app.Application
import android.content.Context
import com.google.android.gms.common.wrappers.InstantApps
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        this.applicationContext?.let { _ ->
            val isInstant = InstantApps.isInstantApp(this)
            if (!isInstant) {
                SplitCompat.install(this)
            }
        }
    }
}
