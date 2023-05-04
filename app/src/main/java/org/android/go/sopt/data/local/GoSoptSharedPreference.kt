package org.android.go.sopt.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.domain.model.UserInfo

class GoSoptSharedPreference(context: Context) {

    private val masterKey = MasterKey
        .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val dataStore: SharedPreferences = if (BuildConfig.DEBUG) context.getSharedPreferences(
        FILE_NAME, Context.MODE_PRIVATE
    ) else EncryptedSharedPreferences.create(
        context,
        FILE_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setUserInfo(user: UserInfo) {
        dataStore.edit().run {
            putString(PREF_USER_ID, user.id)
            putString(PREF_USER_PASSWORD, user.password)
            putString(PREF_USER_NAME, user.name)
            putString(PREF_USER_SPECIALTY, user.specialty)
        }.apply()
    }

    fun getUserInfo(): UserInfo? {
        with(dataStore) {
            val id = getString(PREF_USER_ID, null)
            val password = getString(PREF_USER_PASSWORD, null) ?: ""
            val name = getString(PREF_USER_NAME, null)
            val specialty = getString(PREF_USER_SPECIALTY, null) ?: ""

            if (name == null || id == null) return null

            return UserInfo(id, password, name, specialty)

        }
    }
    fun deleteUserInfo() {
        with(dataStore.edit()) {
            clear()
            commit()
        }
    }

    companion object {
        private const val FILE_NAME = "GO-SOPT"
        private const val PREF_USER_ID = "userId"
        private const val PREF_USER_PASSWORD = "userPassword"
        private const val PREF_USER_NAME = "userName"
        private const val PREF_USER_SPECIALTY = "userSpecialty"
    }
}
