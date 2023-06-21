package org.android.go.sopt.presentation.music

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentGalleryBinding

class PlayListFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val viewModel by viewModels<PlayListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToAddMusicActivity()
        setAdaptor()
    }

    companion object {
        fun newInstance() = PlayListFragment()
    }

    private fun setAdaptor() {
        val playListAdaptor = PlayListAdaptor(requireContext())
        binding.rvPlaylist.adapter = playListAdaptor
        viewModel.playList.flowWithLifecycle(lifecycle).onEach { playList ->
            playListAdaptor.submitList(playList.toMutableList())
        }.launchIn(lifecycleScope)

    }


    private fun moveToAddMusicActivity() {
        binding.fabAddPlaylist.setOnClickListener {
            startActivity(Intent(requireContext(), AddMusicActivity::class.java))
        }
    }

}