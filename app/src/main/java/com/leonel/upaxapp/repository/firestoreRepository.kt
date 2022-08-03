package com.leonel.upaxapp.repository

import android.net.Uri
import android.util.Log
import android.view.View
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import javax.inject.Inject


class firestoreRepository @Inject constructor(){

    val storage = FirebaseStorage.getInstance().getReference()
    var storageRef = storage.storage.reference

    fun subircloud(imageUri: Uri?){
        if(imageUri!=null) {
            val uTask = storageRef.child("images").child(imageUri!!.lastPathSegment!!)
                .putFile(imageUri).addOnCompleteListener {
                    if (it.isSuccessful && it.result != null)
                        Log.d("img subida","subido")
                    //Toast.makeText(this, "Subido con exito", Toast.LENGTH_SHORT).show()
                    else
                        Log.d("img no subida","subido")
                    //Toast.makeText(this, "error al subir", Toast.LENGTH_SHORT).show()

                }
        }
    }

    fun obtenerimagenes(): ArrayList<String>
    {
        var imagelist = ArrayList<String>()
        val listRef = FirebaseStorage.getInstance().reference.child("images")

        listRef.listAll().addOnSuccessListener { listResult ->
            for (file in listResult.items) {
                file.downloadUrl.addOnSuccessListener { uri ->
                    imagelist.add(uri.toString())
                    Log.e("Itemvalue", uri.toString())
                }.addOnSuccessListener {
             return@addOnSuccessListener
                    /*recyclerView.setAdapter(adapter)
                    progressBar.setVisibility(View.GONE)*/
                }
            }
        }
return imagelist
    }
}

