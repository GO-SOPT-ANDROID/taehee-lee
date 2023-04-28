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
        builder.setTitle(getString(R.string.ask_logout))
            .setCancelable(false)
            .setNegativeButton(
                "아니오"
            ) { _, _ ->
                binding.root.showToast(getString(R.string.cancel_logout))
            }
            .setPositiveButton("예") { _, _ ->
                deleteUserData()
                binding.root.showToast(getString(R.string.logout_done))
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
            .create()
        return builder

    }

    private fun deleteUserData() {
        GoSoptSharedPreference(requireContext()).deleteUserInfo()
    }
}