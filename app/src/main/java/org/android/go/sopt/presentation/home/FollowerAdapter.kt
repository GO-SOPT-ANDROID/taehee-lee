package org.android.go.sopt.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemFollowerBinding
import org.android.go.sopt.domain.model.Follower

class FollowerAdapter(context: Context) :
    ListAdapter<Follower, FollowerAdapter.FollowerViewHolder>(FollowerDiffCallback()) {

    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerBinding.inflate(inflater, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class FollowerViewHolder(
        private val binding: ItemFollowerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Follower) {
            binding.follower = data
            binding.executePendingBindings()
        }
    }
}

class FollowerDiffCallback : DiffUtil.ItemCallback<Follower>() {
    override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem == newItem
    }

}