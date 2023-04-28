package org.android.go.sopt.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.GoSoptApplication
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.model.UserInfo
import org.android.go.sopt.util.extension.parcelable
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.extension.showToast

class LoginActivity() : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String? = null
    private var password: String? = null
    private var name: String? = null
    private var specialty: String? = null
    var userInput: UserInfo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSignUpResult()
        clickLoginButton()
        clickSignUpButton()
        binding.layoutLogin.setOnClickListener {
            hideKeyboard()
        }

        autoLogin()

    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

                val userData = result.data ?: return@registerForActivityResult

                val userInfo = userData.parcelable<UserInfo>(USER_INFO)


                if (userInfo != null) {
                    showSnackbar(binding.root, getString(R.string.sign_up_complete_message))
                    GoSoptApplication.prefs.setUserInfo(userInfo)
                    id = userInfo.id
                    password = userInfo.password
                    name = userInfo.name
                    specialty = userInfo.specialty


                }


            }
    }

    private fun checkInfoValid() {
        id?.let { Log.e("회원가입 id값", it) }
        password?.let { Log.e("회원가입 pw값", it) }

        if (binding.etId.text.toString() == id && binding.etPassword.text.toString() == password) {
            showToast(this, getString(R.string.login_success))
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("specialty", specialty)
            startActivity(intent)

        } else {
            showToast(this, getString(R.string.login_fail))
        }
    }

    private fun clickLoginButton() {
        binding.btnLogin.setOnClickListener {
            checkInfoValid()
        }
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
            showToast(this, getString(R.string.auto_login_success_message))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }


    companion object {
        const val USER_INFO = "user_info"
    }

}