package org.android.go.sopt.presentation.gallery

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.ContentUriRequestBody

class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel>()

    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3))
        { imageUriList: List<Uri> ->
            with(binding) {
                when (imageUriList.size) {
                    0 -> {
                        Toast.makeText(requireContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    1 -> {
                        ivGalleryFirst.load(imageUriList[0])
                    }

                    2 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                    }

                    3 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                        ivGalleryThird.load(imageUriList[2])
                    }

                    else -> {
                        Toast.makeText(requireContext(), "3개까지의 이미지만 선택해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                viewModel.setRequestBody(ContentUriRequestBody(requireContext(), imageUriList[0]))
                viewModel.uploadProfileImage()
            }

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
//
//        binding.btnGallerySendImage.setOnClickListener {
//            viewModel.uploadProfileImage()
//        }

    }

    companion object {
        fun newInstance() = GalleryFragment()
    }

}