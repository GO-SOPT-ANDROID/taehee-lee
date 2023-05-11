package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.GoSoptApplication
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.domain.model.UserInfo
import org.android.go.sopt.presentation.HomeActivity
import org.android.go.sopt.presentation.signup.AuthViewModel
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.util.EventObserver
import org.android.go.sopt.util.extension.parcelable
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class LoginActivity() : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<AuthViewModel>()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String? = null
    private var password: String? = null
    private var name: String? = null
    private var specialty: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner

        setSignUpResult()
        clickLoginButton()
        clickSignUpButton()
        binding.layoutLogin.setOnClickListener {
            hideKeyboard()
        }

        autoLogin()
        observeSignInSuccessful()

    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

                val userData = result.data ?: return@registerForActivityResult
                val userInfo = userData.parcelable<UserInfo>(USER_INFO)

                if (userInfo != null) {
                    binding.root.showToast(getString(R.string.sign_up_complete_message))
                    viewModel.setUserInfo(userInfo)
                    id = userInfo.id
                    password = userInfo.password
                    name = userInfo.name
                    specialty = userInfo.specialty
                }

            }
    }

    private fun navigateToHome() {
        binding.root.showToast(getString(R.string.login_success))
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun clickLoginButton() {
        binding.btnLogin.setOnClickListener {
            viewModel.postSignInResult()
        }
    }

    private fun observeSignInSuccessful() {
        viewModel.isCompletedSignIn.observe(this, EventObserver { isSuccess ->
            if (isSuccess) {
                navigateToHome()
            } else {
                binding.root.showToast(getString(R.string.login_fail))
            }
        })
    }

    private fun clickSignUpButton() {
        binding.btnSignUp.setOnClickListener {
            resultLauncher.launch(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun autoLogin() {
        val savedId = GoSoptApplication.prefs.getUserInfo()?.id
        val savedPassword = GoSoptApplication.prefs.getUserInfo()?.password
        val savedName = GoSoptApplication.prefs.getUserInfo()?.name
        val savedSpecialty = GoSoptApplication.prefs.getUserInfo()?.specialty

        if (savedId != null && savedPassword != null) {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("name", savedName)
                putExtra("specialty", savedSpecialty)
            }
            binding.root.showToast(getString(R.string.auto_login_success_message))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val USER_INFO = "user_info"
    }

}