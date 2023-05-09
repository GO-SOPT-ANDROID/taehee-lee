package org.android.go.sopt.presentation.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentGalleryBinding

class GalleryFragment: BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.motionLayout.transitionToEnd()
    }
    
}