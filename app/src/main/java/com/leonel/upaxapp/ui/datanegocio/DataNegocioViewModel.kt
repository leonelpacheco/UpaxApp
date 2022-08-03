package com.leonel.upaxapp.ui.datanegocio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.requestnegocio
import com.leonel.upaxapp.repository.firestoreRepository
import com.leonel.upaxapp.repository.negocioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataNegocioViewModel @Inject constructor(private val repository: negocioRepository): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= invoke()
            if (!result.isNullOrEmpty()) {
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)

        }
    }
    suspend operator fun invoke():List<negocio>{
        val requestneg= requestnegocio(149010)

        val negocios = repository.getAllNegociosFromApi(requestneg)
        return negocios
/*        return if(negocios.isNotEmpty()){
            repository.clearnegocios()
            // repositorytemp.insertnegocio(negocios.map { it.toDataBase() })
            negocios
        }else{
            repository.getAllNegociosFromDatabase()
        }*/
    }
}