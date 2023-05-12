package org.android.go.sopt.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.GoSoptApplication
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentMyPageBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMyPage()
        clickLogoutButton()
    }

    private fun setMyPage() {
        binding.tvName.text = GoSoptApplication.prefs.getUserInfo()?.name.toString()
        binding.tvSpecialty.text = GoSoptApplication.prefs.getUserInfo()?.specialty.toString()
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
                myPageViewModel.logOut()
                binding.root.showToast(getString(R.string.logout_done))
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
            .create()
        return builder

    }

    companion object {
        fun newInstance() = MyPageFragment()
    }

}