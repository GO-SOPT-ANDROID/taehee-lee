package org.android.go.sopt.util.extension

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.makeToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .show()
}