package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.data.local.FollowerState
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.RecyclerViewScrollable
import org.android.go.sopt.util.extension.makeToastMessage
import org.android.go.sopt.util.extension.parcelable

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    RecyclerViewScrollable {
    private var recyclerViewState: Parcelable? = null
    private val viewModel by viewModels<HomeViewModel>()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewState = binding.rvUsers.layoutManager?.onSaveInstanceState()
        recyclerViewState?.let {
            outState.putParcelable("recyclerViewState", it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        recyclerViewState = savedInstanceState?.parcelable("recyclerViewState")
        binding.rvUsers.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }


    private fun setAdapter() {
        val followerTitleAdapter = TitleAdapter(requireContext())
        val followerAdapter = FollowerAdapter(requireContext())
        binding.rvUsers.adapter = ConcatAdapter(followerTitleAdapter, followerAdapter)

        viewModel.followerStateFlow.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                FollowerState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    followerAdapter.submitList(emptyList())
                }

                is FollowerState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    followerAdapter.submitList(state.followerList.toMutableList())
                }

                is FollowerState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    requireContext().makeToastMessage(state.errorMessage)
                }
            }
        }.launchIn(lifecycleScope)
    }

    override fun scrollToTop() {
        binding.rvUsers.layoutManager?.scrollToPosition(0)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }


}