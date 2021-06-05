package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutMenuItemBinding
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.diff.MealDiffCallback
import com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder.MealViewHolder

class MenuAdapter(private val glide: RequestManager, private val onMenuClicked: (Meal) -> Unit): ListAdapter<Meal, MealViewHolder>(MealDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = LayoutMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemBinding, glide) {
            val meal = getItem(it)
            onMenuClicked.invoke(meal)
        }
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}