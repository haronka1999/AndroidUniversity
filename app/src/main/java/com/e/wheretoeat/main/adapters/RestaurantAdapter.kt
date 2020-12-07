package com.e.wheretoeat.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.main.models.Restaurant

class RestaurantAdapter(private var list: MutableList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.DataViewHolder>() {


    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val imageView: ImageView = itemView.findViewById(R.id.restaurantImageView)
        val priceView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return DataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.titleTextView.text = currentItem.name
        holder.imageView.setImageResource(R.drawable.food)
        holder.priceView.text = currentItem.price.toString()
        holder.addressTextView.text = currentItem.address


    }

    // 4.
    override fun getItemCount() = list.size

}