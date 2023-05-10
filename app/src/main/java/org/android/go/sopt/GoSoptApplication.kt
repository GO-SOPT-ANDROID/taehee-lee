package org.android.go.sopt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.android.go.sopt.data.local.GoSoptSharedPreference
import timber.log.Timber

@HiltAndroidApp
class GoSoptApplication: Application() {
    override fun onCreate() {
        prefs = GoSoptSharedPreference(applicationContext)
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var prefs: GoSoptSharedPreference
    }
}