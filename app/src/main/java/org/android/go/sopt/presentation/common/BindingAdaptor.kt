package org.android.go.sopt.presentation.common

import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout
import org.android.go.sopt.R

@BindingAdapter("enabled")
fun setButtonState(button: Button, isValid: String?) {
    when (isValid) {
        "valid" -> button.isEnabled = true
        "invalid" -> button.isEnabled = false
        "empty" -> button.isEnabled = false
        else -> button.isEnabled = false
    }
}

@BindingAdapter("boxStrokeColor")
fun setBoxStrokeColor(textInputLayout: TextInputLayout, isValid: String?) {
    textInputLayout.boxStrokeColor =
        when (isValid) {
            null -> ContextCompat.getColor(textInputLayout.context, R.color.white)
            "empty" -> {
                ContextCompat.getColor(textInputLayout.context, R.color.white)
            }

            "valid" -> {
                ContextCompat.getColor(textInputLayout.context, R.color.primary)
            }

            else -> ContextCompat.getColor(textInputLayout.context, R.color.error)
        }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ShapeableImageView, imageUrl: String) {
    view.load(imageUrl)
}

@BindingAdapter("helperTextColor")
fun setHelperTextColor(textView: TextView, isValid: String?) {
    textView.setTextColor(
        when (isValid) {
            "empty", "valid" -> {
                ContextCompat.getColor(textView.context, R.color.text_helper)
            }
            null -> {
                ContextCompat.getColor(textView.context, R.color.text_helper)
            }
            else -> ContextCompat.getColor(textView.context, R.color.error)
        }
    )
}