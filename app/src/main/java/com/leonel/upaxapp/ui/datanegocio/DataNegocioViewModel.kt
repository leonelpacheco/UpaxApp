package com.leonel.upaxapp.ui.datanegocio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.upaxapp.database.entities.toDataBase
import com.leonel.upaxapp.model.empleado
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
    val listnegocioModel = MutableLiveData<List<negocio>>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= invoke()
            if (!result.isNullOrEmpty()) {
                listnegocioModel.postValue(result!!)
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
        return if(negocios.isNotEmpty()){
            repository.clearnegocios()
            //repository.insertnegocio(negocios.map { it.toDataBase() })
            negocios
        }else{
            repository.getAllNegociosFromDatabase()
        }
    }

    //*************Inserción manual para pruebas por fallo en api**********

    fun insertarusuario(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= invokeinsert()
            if (!result.isNullOrEmpty()) {
                listnegocioModel.postValue(result!!)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)

        }
    }

    suspend  fun invokeinsert():List<negocio>{
        var negocioinsert = negocio("30","78","12","97000","Mexico"
        ,"Yucatan","Merida","Centro","20.9803289","-89.7730065","https://sucursales.net/wp-content/uploads/2020/11/starbucks-en-Merida.jpg")
        repository.insertnegocio(negocioinsert.toDataBase())

        return repository.getAllNegociosFromDatabase()

    }
    //****************
}