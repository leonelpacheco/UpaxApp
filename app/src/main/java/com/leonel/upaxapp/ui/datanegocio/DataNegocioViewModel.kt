package com.leonel.upaxapp.ui.datanegocio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.upaxapp.database.entities.toDataBase
import com.leonel.upaxapp.model.Comercio
import com.leonel.upaxapp.model.Comerciosave
import com.leonel.upaxapp.model.negocio
import com.leonel.upaxapp.network.requestnegocio
import com.leonel.upaxapp.repository.negocioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataNegocioViewModel @Inject constructor(private val repository: negocioRepository): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val listnegocioModel = MutableLiveData<List<negocio>?>()
    val listcomercioModel = MutableLiveData<Comercio>()

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
        //return negocios
        return if(negocios.isNotEmpty()){
            repository.clearnegocios()
            repository.insertnegocio(negocios.map { it.toDataBase() })
            negocios
        }else{
            repository.getAllNegociosFromDatabase()
        }
    }
    //******************Obtener Comercio**************

    fun onComercio(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= invokecomercio()
            if (result!=null) {
                listcomercioModel.postValue(result!!)
                listnegocioModel.postValue(result.direcciones)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)

        }
    }
    suspend fun invokecomercio(): Comercio{
        val requestneg= requestnegocio(149010)

        val comercio = repository.getAllComercioFromApi(requestneg)
        return if(comercio!=null){
            repository.clearcomercio()
            repository.clearnegocios()
            val savecomercio = Comerciosave(comercio.fiIdZeus,comercio.fcIdComercio,comercio.nombre,
            comercio.descripcion,comercio.email,comercio.telefono,comercio.tipoComercio,comercio.idGiro,
            comercio.idCategoria,comercio.idSubcategoria,comercio.idDisponibilidad,comercio.idEstatusLevantamiento,
            comercio.urlImagen)
            repository.insertcomercio(savecomercio.toDataBase())
            comercio.direcciones?.let { repository.insertnegocio(it.map { it.toDataBase() }) }
            comercio
        }else{
            val direcciones=repository.getAllNegociosFromDatabase()
            val comercio = repository.getAllComerciosFromDatabase().get(0)
            val enviarcomercio = Comercio(comercio.fiIdZeus,comercio.fcIdComercio,comercio.nombre,
    comercio.descripcion,comercio.email,comercio.telefono,comercio.tipoComercio,comercio.idGiro,
    comercio.idCategoria,comercio.idSubcategoria,comercio.idDisponibilidad,comercio.idEstatusLevantamiento,
    comercio.urlImagen,direcciones)
            return enviarcomercio

        }
    }



    //**********************************************
    //*************Inserción manual para pruebas por fallo en api**********

 /*   fun insertarusuario(){
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
       repository.insertnegocio(negociosinsert.toDataBase())

        return repository.getAllNegociosFromDatabase()

    }*/
    //****************
}