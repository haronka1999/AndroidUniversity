package com.e.wheretoeat.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.main.models.ApiRestaurant

/*
This adapter attach the recycler_view item with
the Restaurant recycler views
in the home fragment and in the favorite list
 */

class RestaurantAdapter(
    private var list: MutableList<ApiRestaurant>,
    private val listener: OnItemClickListener,
) :
    RecyclerView.Adapter<RestaurantAdapter.DataViewHolder>() {

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        val imageView: ImageView = itemView.findViewById(R.id.restaurantImageView)
        val priceView: TextView = itemView.findViewById(R.id.priceTextView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(list[position])
            }
        }
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

        val fullAddress = "Address: ${currentItem.address}"
        val priceTag = "Price: ${currentItem.price}"

        holder.priceView.text = priceTag
        holder.addressTextView.text = fullAddress
    }

    // 4.
    override fun getItemCount() = list.size

    interface OnItemClickListener {
        fun onItemClick(item: ApiRestaurant)
    }

}

