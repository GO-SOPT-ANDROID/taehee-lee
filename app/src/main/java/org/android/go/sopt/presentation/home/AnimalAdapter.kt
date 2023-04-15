package org.android.go.sopt.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemAnimalBinding
import org.android.go.sopt.model.Animal

class AnimalAdapter(context: Context) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }
    private var animalList: List<Animal> = emptyList()

    class AnimalViewHolder(
        private val binding: ItemAnimalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Animal) {
            binding.ivTibet.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvAnimal.text = data.animal
            binding.tvSpecies.text = data.species
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(inflater, parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.onBind(animalList[position])
    }

    override fun getItemCount() = animalList.size

    fun setAnimalList(animalList: List<Animal>){

        this.animalList = animalList
        notifyDataSetChanged()
    }


}