package com.leonel.upaxapp.adapters

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leonel.upaxapp.R
import com.leonel.upaxapp.model.empleado


class imageAdapter (private val mList: List<String>): RecyclerView.Adapter<imageAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_images, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Items = mList[position]

        //if(ItemsViewModel.image.isNotEmpty())
        val imageUri = Uri.parse(Items)
        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.poster_path)

        // sets the text to the textview from our itemHolder class

/*        if(imageUri != null)
            holder.imageView.setImageURI(imageUri)
        else
            holder.imageView.setImageResource(R.drawable.ic_person)*/

   Glide.with(holder.itemView.getContext()).load(mList.get(position)).into(holder.imageView);


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.item)
    }
}