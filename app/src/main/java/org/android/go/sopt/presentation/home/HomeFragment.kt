package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.RecyclerViewScrollable
import org.android.go.sopt.util.extension.parcelable

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    RecyclerViewScrollable {
    private var recyclerViewState: Parcelable? = null
    private val viewModel by viewModels<HomeViewModel>()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewState = binding.rvAnimals.layoutManager?.onSaveInstanceState()
        recyclerViewState?.let {
            outState.putParcelable("recyclerViewState", it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        recyclerViewState = savedInstanceState?.parcelable("recyclerViewState")
        binding.rvAnimals.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    private fun setAdapter() {
        val animalTitleAdapter = TitleAdapter(requireContext())
        val animalAdapter = AnimalAdapter(requireContext())
        binding.rvAnimals.adapter = ConcatAdapter(animalTitleAdapter, animalAdapter)
        animalAdapter.submitList(viewModel.mockAnimalList)
    }

    override fun scrollToTop() {
        binding.rvAnimals.layoutManager?.scrollToPosition(0)
    }


}