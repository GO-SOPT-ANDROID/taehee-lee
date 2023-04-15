package org.android.go.sopt.model

import androidx.annotation.DrawableRes

data class Animal(
    val id:Long,
    @DrawableRes val image: Int,
    val animal : String,
    val species: String
)
