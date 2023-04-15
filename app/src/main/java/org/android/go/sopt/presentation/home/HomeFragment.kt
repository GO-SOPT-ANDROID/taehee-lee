package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.RecyclerViewScrollable
import org.android.go.sopt.util.extension.parcelable

class HomeFragment : Fragment(), RecyclerViewScrollable {
    private var recyclerViewState: Parcelable? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewState = binding.rvAnimals.layoutManager?.onSaveInstanceState()
        recyclerViewState?.let {
            outState.putParcelable("recyclerViewState", it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        recyclerViewState = savedInstanceState?.parcelable("recyclerViewState")
        binding.rvAnimals.layoutManager?.onRestoreInstanceState(recyclerViewState)
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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