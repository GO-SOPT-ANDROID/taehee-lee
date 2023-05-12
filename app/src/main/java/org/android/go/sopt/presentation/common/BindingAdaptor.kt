package org.android.go.sopt.presentation.common

import android.widget.Button
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("enabled")
fun setButtonState(button: Button, isValid: String?) {
    when (isValid) {
        "valid" -> button.isEnabled = true
        "invalid" -> button.isEnabled = false
        else -> button.isEnabled = false
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ShapeableImageView, imageUrl: String) {
    view.load(imageUrl)
}