package org.android.go.sopt

import android.app.Application
import org.android.go.sopt.model.GoSoptSharedPreference

class GoSoptApplication: Application() {
    override fun onCreate() {
        prefs = GoSoptSharedPreference(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var prefs: GoSoptSharedPreference
    }
}