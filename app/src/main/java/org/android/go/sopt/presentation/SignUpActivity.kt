package org.android.go.sopt.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.model.UserInfo
import org.android.go.sopt.presentation.LoginActivity.Companion.USER_INFO

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickSignUpCompleteBtn()
        binding.layoutSignUp.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun checkSignUpValid(): Boolean {
        return ((binding.etId.text.length in 6..12)
                && (binding.etPassword.text.length in 8..12)
                && binding.etName.text.isNotEmpty()
                && binding.etSpecialty.text.isNotEmpty())

    }

    private fun passUserData() {
        if (checkSignUpValid()) {
            val userInfo = UserInfo(
                binding.etId.text.toString(),
                binding.etPassword.text.toString(),
                binding.etName.text.toString(),
                binding.etSpecialty.text.toString()
            )

            intent.putExtra(USER_INFO, userInfo)
            setResult(Activity.RESULT_OK, intent)
            if (!isFinishing) finish()
        }
    }

    private fun clickSignUpCompleteBtn() {
        binding.btnSignUpComplete.setOnClickListener {
            passUserData()
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}