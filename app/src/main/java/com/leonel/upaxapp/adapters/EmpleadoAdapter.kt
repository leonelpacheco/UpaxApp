package com.leonel.upaxapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leonel.upaxapp.R
import com.leonel.upaxapp.model.empleado

class EmpleadoAdapter (private val mList: List<empleado>): RecyclerView.Adapter<EmpleadoAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_empleado, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        //if(ItemsViewModel.image.isNotEmpty())
        val imageUri = Uri.parse(ItemsViewModel.imagen)
        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.poster_path)

        // sets the text to the textview from our itemHolder class
        holder.txt_empleado.text = ItemsViewModel.id.toString()
        holder.txt_name.text = ItemsViewModel.nombre
        holder.txt_phone.text = ItemsViewModel.telefono
        holder.txt_email.text = ItemsViewModel.email
        holder.txt_address.text = ItemsViewModel.direccion
        if(imageUri != null)
            holder.imageView.setImageURI(imageUri)
        else
            holder.imageView.setImageResource(R.drawable.ic_person)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.fotoempleadorow)
        val txt_empleado: TextView = itemView.findViewById(R.id.txt_empleado)
        val txt_name: TextView = itemView.findViewById(R.id.txt_nombre)
        val txt_phone: TextView = itemView.findViewById(R.id.txt_telefono)
        val txt_email: TextView = itemView.findViewById(R.id.txt_email)
        val txt_address: TextView = itemView.findViewById(R.id.txt_direccion)
    }
}