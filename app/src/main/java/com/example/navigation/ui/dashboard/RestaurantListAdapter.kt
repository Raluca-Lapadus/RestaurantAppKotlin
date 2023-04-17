package com.example.navigation.ui.dashboard

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.RestaurantItemBinding

class RestaurantListAdapter(
    private var restaurantsList: MutableList<RestaurantItem>,
    private val context: Context
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

    fun filterList(newText: String) {
        val list = mutableListOf<RestaurantItem>()
        for ( item in restaurantsList) {
            if(item.title.toLowerCase().contains(newText.toLowerCase())){
                list.add(item)
            }
        }
        if(list.isEmpty()){
            Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
        }
        else {
            setFilteredList(list)
        }
    }

    fun setFilteredList(filteredList: MutableList<RestaurantItem>) {
        this.restaurantsList = filteredList
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
            btnShare?.setOnClickListener {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This restaurant called ${curRestaurant.title} is my favorite")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Share message via")
                context.startActivity(shareIntent)
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