package org.android.go.sopt.presentation.mypage

import android.os.Bundle
import android.view.View
import org.android.go.sopt.GoSoptApplication
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentMyPageBinding

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMyPage()
    }

    private fun setMyPage() {
        binding.tvName.text = GoSoptApplication.prefs.getUserInfo()?.name
        binding.tvSpecialty.text = GoSoptApplication.prefs.getUserInfo()?.specialty
    }
}