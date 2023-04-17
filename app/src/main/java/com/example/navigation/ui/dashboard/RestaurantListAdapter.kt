package com.example.navigation.ui.dashboard

import android.content.Intent
import android.graphics.Paint
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.ActivityMainBinding
import com.example.navigation.databinding.FragmentDashboardBinding
import com.example.navigation.databinding.RestaurantItemBinding

class RestaurantListAdapter(
    private val restaurantsList: MutableList<RestaurantItem>,

) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantsListViewHolder>() {

    class RestaurantsListViewHolder(val binding: RestaurantItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsListViewHolder {
        val itemBinding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsListViewHolder(itemBinding)
    }

    fun addRestaurant(restaurant: RestaurantItem) {
        restaurantsList.add(restaurant)
        notifyItemInserted(restaurantsList.size - 1)
    }

    fun deleteRestaurant() {
        restaurantsList.removeAll { restaurant ->
            restaurant.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RestaurantsListViewHolder, position: Int) {
        val curRestaurant = restaurantsList[position]
        holder.binding.apply {
            tvRestaurantTitlee.text = curRestaurant.title
            cbDone.isChecked = curRestaurant.isChecked
            toggleStrikeThrough(tvRestaurantTitlee, curRestaurant.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvRestaurantTitlee, isChecked)
                curRestaurant.isChecked = !curRestaurant.isChecked
            }
        }
    }

    private fun toggleStrikeThrough(tvRestaurantTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvRestaurantTitle.paintFlags = tvRestaurantTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvRestaurantTitle.paintFlags = tvRestaurantTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }

}