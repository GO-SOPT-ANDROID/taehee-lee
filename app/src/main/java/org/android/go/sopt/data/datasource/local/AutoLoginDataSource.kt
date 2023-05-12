package org.android.go.sopt.data.datasource.local

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AutoLoginDataSource @Inject constructor(
    private val pref: SharedPreferences,
) {

    var isAutoLogin: Boolean
        set(value) = pref.edit { putBoolean(AUTO_LOGIN_KEY, value) }
        get() = pref.getBoolean(AUTO_LOGIN_KEY, false)

    fun clearPref() = pref.edit {
        clear()
    }

    companion object {
        const val AUTO_LOGIN_KEY = "자로"
    }
}