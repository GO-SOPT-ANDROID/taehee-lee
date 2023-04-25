package org.android.go.sopt

import android.app.Application
import org.android.go.sopt.model.GoSoptSharedPreference
import timber.log.Timber

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