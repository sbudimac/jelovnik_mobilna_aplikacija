package com.example.rsrafprojekat2stefan_budimac_rn0618.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rsrafprojekat2stefan_budimac_rn0618.data.model.Meal
import com.example.rsrafprojekat2stefan_budimac_rn0618.databinding.LayoutMenuItemBinding

class MealViewHolder(private val itemBinding: LayoutMenuItemBinding, private val glide: RequestManager, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {
    init {
        itemBinding.root.setOnClickListener {
            onItemClicked.invoke(absoluteAdapterPosition)
        }
    }

    fun bind(meal: Meal) {
        itemBinding.mealTitle.text = meal.title
        itemBinding.mealDate.text = meal.date.toString()
        itemBinding.mealCategory.text = meal.category
        glide.load(meal.image).dontAnimate().into(itemBinding.mealImg)
    }
}