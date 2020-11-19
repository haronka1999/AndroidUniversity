package com.e.wheretoeat.main.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.wheretoeat.main.models.Restaurant

class DataAdapter(private val list: List<Restaurant>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    // 1. user defined ViewHolder type
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.image_view)
//        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
//        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
    }

    // 2. Called only a few times = number of items on screen + a few more ones
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) {
//        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
//        //  return DataViewHolder(itemView)
//
//    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        val currentItem = list[position]
//        holder.imageView.setImageResource(currentItem.imageResource)
//        holder.textView1.text = currentItem.text1
//        holder.textView2.text = currentItem.text2
    }


    // 4.
    override fun getItemCount() = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        TODO("Not yet implemented")
    }

}