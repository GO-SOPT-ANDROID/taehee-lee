package org.android.go.sopt.presentation.music

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import coil.load
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingActivity
import org.android.go.sopt.databinding.ActivityAddMusicBinding
import org.android.go.sopt.util.ContentUriRequestBody
import org.android.go.sopt.util.extension.showSnackbar

class AddMusicActivity : BindingActivity<ActivityAddMusicBinding>(R.layout.activity_add_music) {

    private val viewModel by viewModels<MusicViewModel>()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) showSnackbar(binding.root, "허가")
        else showSnackbar(binding.root, "불허")
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.GetContent())
        { imageUri: Uri? ->
            binding.ivMusicThumbnail.load(imageUri)
            viewModel.setRequestBody(ContentUriRequestBody(this, imageUri!!))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.root.setOnClickListener {
            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
        }

        binding.btnRegisterMusic.setOnClickListener {
            viewModel.uploadMusic(binding.etMusicTitle.text.toString(), binding.etSinger.text.toString())
            val intent = Intent(this, PlayListFragment::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}