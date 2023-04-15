package org.android.go.sopt.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityMyPageBinding
import org.android.go.sopt.presentation.gallery.GalleryFragment
import org.android.go.sopt.presentation.home.HomeFragment
import org.android.go.sopt.presentation.search.SearchFragment

class MyPageActivity : BindingActivity<ActivityMyPageBinding>(R.layout.activity_my_page) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initLayout()
        selectFragment()
        reselectFragment()

    }

    private fun initLayout() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.home_container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.home_container, HomeFragment())
                .commit()
            binding.bnvHome.selectedItemId = R.id.home_menu
        }
    }

    private fun selectFragment() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> {
                    changeFragment(HomeFragment())
                }
                R.id.gallery_menu -> {
                    changeFragment(GalleryFragment())
                }
                R.id.search_menu -> {
                    changeFragment(SearchFragment())
                }
                else -> error("item id: {${it.itemId} is cannot be selected")
            }
            return@setOnItemSelectedListener true
        }
    }


    private fun reselectFragment() {
        binding.bnvHome.setOnItemReselectedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.home_container)
            if (currentFragment is RecyclerViewScrollable) {
                currentFragment.scrollToTop()
            }
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_container, fragment)
            .commit()
    }

}