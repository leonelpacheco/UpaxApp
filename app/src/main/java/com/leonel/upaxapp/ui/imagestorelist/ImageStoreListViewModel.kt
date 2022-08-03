package com.leonel.upaxapp.ui.imagestorelist

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.leonel.upaxapp.adapters.imageAdapter
import com.leonel.upaxapp.model.empleado
import com.leonel.upaxapp.repository.firestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageStoreListViewModel @Inject constructor(private val repository: firestoreRepository): ViewModel() {
    val listimagesModel = MutableLiveData<ArrayList<String>>()
    val isLoading = MutableLiveData<Boolean>()

    fun obtenerimagenes()
    {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = invoke()
            if (!result.isNullOrEmpty()) {
                listimagesModel.postValue(result!!)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }
    }

    fun invoke():ArrayList<String>{
        //******************

        var imageList= ArrayList<String>()
        val storage = Firebase.storage
        val listRef = storage.reference.child("images")
        listRef.listAll().addOnSuccessListener { listResult ->
            for (file in listResult.items) {
                file.downloadUrl.addOnSuccessListener { uri -> // adding the url in the arraylist
                    imageList.add(uri.toString())
                    Log.e("Itemvalue", uri.toString())
                }.addOnSuccessListener {
                    listimagesModel.postValue(imageList)
                }
            }
        }
        //*******************
        return imageList
    }


}