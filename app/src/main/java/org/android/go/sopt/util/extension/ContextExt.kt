package org.android.go.sopt.util.extension

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String, isShort: Boolean = true) {
    val duration = if (isShort) {
        Toast.LENGTH_SHORT
    } else {
        Toast.LENGTH_LONG
    }
    Toast.makeText(context, message, duration).show()
}