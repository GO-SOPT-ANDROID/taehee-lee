package org.android.go.sopt.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.databinding.ItemAnimalBinding
import org.android.go.sopt.domain.model.Animal

class AnimalAdapter(context: Context) :
    ListAdapter<Animal, AnimalAdapter.AnimalViewHolder>(AnimalDiffCallback()) {

    private val inflater by lazy { LayoutInflater.from(context) }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(inflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class AnimalViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Animal) {
            binding.ivTibet.load(data.image)
            binding.tvAnimal.text = data.animal
            binding.tvSpecies.text = data.species
        }

    }

}

class AnimalDiffCallback : DiffUtil.ItemCallback<Animal>() {
    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.animal == newItem.animal
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem == newItem
    }

}