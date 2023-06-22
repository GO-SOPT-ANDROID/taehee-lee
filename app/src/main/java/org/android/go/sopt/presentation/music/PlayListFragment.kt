package org.android.go.sopt.presentation.music

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentGalleryBinding
import timber.log.Timber

class PlayListFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val viewModel by viewModels<MusicViewModel>()
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveToAddMusicActivity()
        addMusicToPlayList()
        setAdaptor()
    }

    private fun addMusicToPlayList() {
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != Activity.RESULT_OK) return@registerForActivityResult
                Timber.e("result : ${result.data?.getStringExtra("title")}")
                setAdaptor()

            }
    }

    private fun setAdaptor() {
        val playListAdaptor = PlayListAdaptor(requireContext())
        viewModel.fetchPlayList()
        binding.rvPlaylist.adapter = playListAdaptor
        viewModel.playList.flowWithLifecycle(lifecycle).onEach { playList ->
            playListAdaptor.submitList(playList.toMutableList())
        }.launchIn(lifecycleScope)

    }


    private fun moveToAddMusicActivity() {
        binding.fabAddPlaylist.setOnClickListener {
            launcher.launch(Intent(requireContext(), AddMusicActivity::class.java))
        }
    }

    companion object {
        fun newInstance() = PlayListFragment()
    }

}