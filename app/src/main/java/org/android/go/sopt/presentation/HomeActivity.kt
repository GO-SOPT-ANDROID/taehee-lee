package org.android.go.sopt.presentation

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityHomeBinding
import org.android.go.sopt.presentation.music.PlayListFragment
import org.android.go.sopt.presentation.home.HomeFragment
import org.android.go.sopt.presentation.mypage.MyPageFragment
import org.android.go.sopt.presentation.search.SearchFragment
import org.android.go.sopt.util.extension.replace
import timber.log.Timber

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initLayout()
        selectFragment()
        reselectFragment()

    }

    private fun initLayout() {
        changeFragment(R.id.home_menu)
        binding.bnvHome.selectedItemId = R.id.home_menu
    }

    private fun selectFragment() {
        binding.bnvHome.run {
            setOnItemSelectedListener {
                changeFragment(it.itemId)
                true
            }
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

    private fun changeFragment(menuItemId: Int) = when (menuItemId) {
        R.id.home_menu -> replace<HomeFragment>(
            R.id.home_container,
            HomeFragment::class.java.simpleName
        )
        R.id.gallery_menu -> replace<PlayListFragment>(R.id.home_container)
        R.id.search_menu -> replace<SearchFragment>(R.id.home_container)
        R.id.my_page_menu -> replace<MyPageFragment>(R.id.home_container)
        else -> Timber.e(IllegalArgumentException("Not found menu item id"))
    }

}