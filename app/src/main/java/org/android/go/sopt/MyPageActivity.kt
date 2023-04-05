package org.android.go.sopt

import android.os.Bundle
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityMyPageBinding

class MyPageActivity : BindingActivity<ActivityMyPageBinding>(R.layout.activity_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMyPage()
    }

    private fun setMyPage(){
        binding.tvName.text = intent.getStringExtra("name")
        binding.tvSpecialty.text = intent.getStringExtra("specialty")
    }
}