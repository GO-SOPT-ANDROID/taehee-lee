package org.android.go.sopt.presentation.common

import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("enabled")
fun setButtonState(button: Button, isValid: String?) {
    when (isValid) {
        "valid" -> button.isEnabled = true
        "invalid" -> button.isEnabled = false
        else -> button.isEnabled = false
    }
}