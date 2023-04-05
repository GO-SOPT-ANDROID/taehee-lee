package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.model.UserInfo
import org.android.go.sopt.util.extension.parcelable
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.extension.showToast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var id: String? = null
    private var password: String? = null
    private var name: String? = null
    private var specialty: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSignUpResult()
        clickLoginButton()
        clickSignUpButton()

    }

    private fun setSignUpResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult

                val userData = result.data ?: return@registerForActivityResult

                val userInfo = userData.parcelable<UserInfo>(USER_INFO)

                if (userInfo != null) {
                    showSnackbar(binding.root, getString(R.string.sign_up_complete_message))

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
            val intent = Intent(this, MyPageActivity::class.java)
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


    companion object {
        const val USER_INFO = "user_info"
    }

}