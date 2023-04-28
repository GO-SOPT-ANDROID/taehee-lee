package org.android.go.sopt.util.extension

import android.view.View
import android.widget.Toast

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        .show()
}