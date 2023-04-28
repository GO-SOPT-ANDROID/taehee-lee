package org.android.go.sopt.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import org.android.go.sopt.GoSoptApplication
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentMyPageBinding
import org.android.go.sopt.model.GoSoptSharedPreference
import org.android.go.sopt.presentation.LoginActivity
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.extension.showToast

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMyPage()
        clickLogoutButton()
    }

    private fun setMyPage() {
        binding.tvName.text = GoSoptApplication.prefs.getUserInfo()?.name
        binding.tvSpecialty.text = GoSoptApplication.prefs.getUserInfo()?.specialty
    }

    private fun clickLogoutButton() {
        binding.btnLogout.setOnClickListener {
            showLogoutDialog().show()
        }
    }

    private fun showLogoutDialog(): AlertDialog.Builder {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("로그아웃 하시겠습니까?")
            .setCancelable(false)
            .setNegativeButton(
                "아니오"
            ) { _, _ ->
                showToast(requireContext(), "로그아웃을 취소합니다.")
            }
            .setPositiveButton("예") { p0, p1 ->
                deleteUserData()
                showSnackbar(binding.root, "로그아웃 하였습니다.")
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
            .create()
        return builder

    }

    private fun deleteUserData() {
        GoSoptSharedPreference(requireContext()).deleteUserInfo()
    }
}