package org.android.go.sopt.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.model.KakaoSearchResponseDto.Document
import org.android.go.sopt.databinding.ItemSearchResultBinding

class SearchAdaptor(context: Context) :
    ListAdapter<Document, SearchAdaptor.SearchViewHolder>(SearchDiffCallback()) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchResultBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class SearchViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Document) {
            with(binding) {
                tvSearchResultTitle.text =
                    HtmlCompat.fromHtml(data.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
                tvSearchResultUrl.text = data.url
            }
        }
    }

    class SearchDiffCallback : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.contents == newItem.contents
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }

    }
}