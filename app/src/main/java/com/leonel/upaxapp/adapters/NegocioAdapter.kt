package com.leonel.upaxapp.adapters

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leonel.upaxapp.R
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.ui.datanegocio.NegocioMapsFragment
import com.leonel.upaxapp.utils.ChangeFragment


class NegocioAdapter (private val mList: List<negocio>, context:FragmentActivity): RecyclerView.Adapter<NegocioAdapter.ViewHolder>() {
    val context= context
    val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_negocio, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        //if(ItemsViewModel.image.isNotEmpty())
        val imageUri = Uri.parse(ItemsViewModel.urlImagen)
        // sets the image to the imageview from our itemHolder class
        //holder.imageView.setImageResource(ItemsViewModel.poster_path)

        // sets the text to the textview from our itemHolder class
        holder.txt_direccion.text = ItemsViewModel.calle
        holder.txt_exterior.text = ItemsViewModel.numero_exterior
        holder.txt_interior.text = ItemsViewModel.numero_interior
        holder.txt_cp.text = ItemsViewModel.codigo_postal
        holder.txt_colonia.text = ItemsViewModel.nombreColonia
        holder.txt_municipio.text = ItemsViewModel.nombreMunicipio
        holder.txt_estado.text = ItemsViewModel.nombreEstado
        holder.txt_pais.text = ItemsViewModel.nombrePais


        if(imageUri != null)
           // holder.imageView.setImageURI(imageUri)
        Glide.with(holder.itemView.getContext()).load(mList.get(position)).into(holder.imageView);
        else
            holder.imageView.setImageResource(R.drawable.ic_person)

        holder.btn_verubicacion.setOnClickListener(View.OnClickListener {

            val bundle = Bundle()
            bundle.putString("latitud", ItemsViewModel.latitud)
            bundle.putString("longitud", ItemsViewModel.longitud)
            val fragmentapp: Fragment =
                NegocioMapsFragment()
                ChangeFragment.changewhitData(NegocioMapsFragment(), manager  , bundle)

        })

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imagennegocio)
        val txt_direccion: TextView = itemView.findViewById(R.id.txt_direccion)
        val txt_colonia: TextView = itemView.findViewById(R.id.txt_colonia)
        val txt_exterior: TextView = itemView.findViewById(R.id.txt_exterior)
        val txt_interior: TextView = itemView.findViewById(R.id.txt_interior)
       val txt_cp: TextView = itemView.findViewById(R.id.txt_cp)
        val txt_municipio: TextView = itemView.findViewById(R.id.txt_municipio)
        val txt_pais: TextView = itemView.findViewById(R.id.txt_pais)
        val txt_estado: TextView = itemView.findViewById(R.id.txt_estado)
        val btn_verubicacion: Button = itemView.findViewById(R.id.btn_verubicacion)

    }
}