package org.android.go.sopt.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemTitleBinding

class TitleAdapter(context: Context) : RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    class TitleViewHolder(private val binding: ItemTitleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val binding = ItemTitleBinding.inflate(inflater, parent,false)
        return TitleViewHolder(binding)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {

    }


}