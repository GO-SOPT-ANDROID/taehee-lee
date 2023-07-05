package org.android.go.sopt.presentation.music

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPlaylistBinding
import org.android.go.sopt.domain.model.Music

class PlayListAdaptor(context: Context) :
    ListAdapter<Music, PlayListAdaptor.PlayListViewHolder>(PlayListDiffCallback()) {

    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListAdaptor.PlayListViewHolder {
        val binding = ItemPlaylistBinding.inflate(inflater, parent, false)
        return PlayListAdaptor.PlayListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayListAdaptor.PlayListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class PlayListViewHolder(
        private val binding: ItemPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Music) {
            binding.music = data
            binding.executePendingBindings()
        }
    }
}

class PlayListDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }

}