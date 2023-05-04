package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.domain.model.UserInfo
import org.android.go.sopt.presentation.login.LoginActivity.Companion.USER_INFO

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        clickSignUpCompleteBtn()
        binding.layoutSignUp.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun passUserData() {
        if (binding.btnSignUpComplete.isEnabled) {
            val userInfo = UserInfo(
                viewModel.id.toString(),
                viewModel.password.toString(),
                viewModel.name.toString(),
                viewModel.specialty.toString()
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