package org.android.go.sopt.presentation.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.android.go.sopt.R
import org.android.go.sopt.base.BindingFragment
import org.android.go.sopt.data.datasource.remote.ServicePool.kakaoSearchService
import org.android.go.sopt.data.model.KakaoSearchResponseDto
import org.android.go.sopt.databinding.FragmentSearchBinding
import org.android.go.sopt.util.extension.makeToastMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private var adapter: SearchAdaptor? = null
    private var debounceJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setSearchViewQueryTextChangeEvent()
    }

    private fun initAdapter() {
        adapter = SearchAdaptor(requireContext())
        binding.rvSearchResult.adapter = adapter
    }

    private fun setSearchViewQueryTextChangeEvent() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                debounceSearch(query)
                return true
            }

        })
    }

    private fun debounceSearch(query: String?) {
        debounceJob?.cancel()
        debounceJob = viewLifecycleOwner.lifecycleScope.launch {
            delay(DEBOUNCE_DELAY)
            if (!query.isNullOrBlank()) {
                getKakaoSearchResult(query)
            } else {
                adapter?.submitList(emptyList())
            }
        }
    }

    private fun getKakaoSearchResult(query: String) {
        kakaoSearchService.getSearchWeb(keyword = query).enqueue(
            object : Callback<KakaoSearchResponseDto> {
                override fun onResponse(
                    call: Call<KakaoSearchResponseDto>,
                    response: Response<KakaoSearchResponseDto>
                ) {
                    if (response.isSuccessful) {
                        adapter?.submitList(
                            response.body()?.documents ?: emptyList()
                        )
                    } else {
                        requireContext().makeToastMessage("${response.code()}")
                    }
                }

                override fun onFailure(call: Call<KakaoSearchResponseDto>, t: Throwable) {
                    requireContext().makeToastMessage("연결에 실패했습니다.")
                }
            }
        )
    }

    companion object {
        fun newInstance() = SearchFragment()
        const val DEBOUNCE_DELAY: Long = 300

    }

}