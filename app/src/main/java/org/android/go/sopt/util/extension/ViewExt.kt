package org.android.go.sopt.util.extension

import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, message: String, isShort: Boolean = true) {
    val duration = if (isShort) {
        Snackbar.LENGTH_SHORT
    } else {
        Snackbar.LENGTH_LONG
    }
    Snackbar.make(view, message, duration).show()
}

fun View.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT)
        .show()
}