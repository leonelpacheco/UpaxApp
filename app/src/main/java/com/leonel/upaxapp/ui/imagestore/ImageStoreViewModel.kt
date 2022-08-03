package com.leonel.upaxapp.ui.imagestore

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.upaxapp.database.entities.toDataBase
import com.leonel.upaxapp.model.add
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.requestnegocio
import com.leonel.upaxapp.repository.firestoreRepository
import com.leonel.upaxapp.repository.negocioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageStoreViewModel @Inject constructor(private val repository: firestoreRepository): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    fun enviarImg(imageUri: Uri?)
    {
        viewModelScope.launch {
            isLoading.postValue(true)
            repository.subircloud(imageUri)
            isLoading.postValue(false)
        }
    }

}