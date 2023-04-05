package org.android.go.sopt

import android.app.Activity
import android.os.Bundle
import org.android.go.sopt.LoginActivity.Companion.USER_INFO
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.model.UserInfo

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickSignUpCompleteBtn()
    }

    private fun checkSignUpValid(): Boolean {
        return ((binding.etId.text.length in 6..12)
                && (binding.etPassword.text.length in 8..12)
                && binding.etName.text.isNotEmpty()
                && binding.etSpecialty.text.isNotEmpty())

    }

    private fun passUserData(){
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


}