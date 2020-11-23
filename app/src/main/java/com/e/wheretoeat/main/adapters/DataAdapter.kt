package com.e.wheretoeat.main.adapters

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.R
import com.e.wheretoeat.main.models.Restaurant

class DataAdapter(private val list: List<Restaurant>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    // 1. user defined ViewHolder type
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(restaurant : Restaurant){

            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)

            restaurant.title = titleTextView.text.toString()
            restaurant.address = addressTextView.text.toString()
           // restaurant.image = imageView.setImageResource(Im)



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
        holder.bindItems(list[position])
    }

    // 4.
    override fun getItemCount() = list.size

}