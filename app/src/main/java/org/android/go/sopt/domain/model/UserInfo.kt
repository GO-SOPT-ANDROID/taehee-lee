package org.android.go.sopt.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val password: String,
    val name: String,
    val specialty: String
) : Parcelable
