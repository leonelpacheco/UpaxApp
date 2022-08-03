package com.leonel.upaxapp.ui.dataempleado

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.upaxapp.database.entities.toDataBase
import com.leonel.upaxapp.model.empleado
import com.leonel.upaxapp.repository.empleadoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadoViewModel @Inject constructor(private val repository: empleadoRepository): ViewModel() {
    val listempleadoModel = MutableLiveData<List<empleado>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreateEmpleado(empleadoinsert: empleado){

        viewModelScope.launch {
            isLoading.postValue(true)
            val result= invoke(empleadoinsert)
            if (!result.isNullOrEmpty()) {
                listempleadoModel.postValue(result!!)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)

        }

    }
    fun consultarempleados()
    {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= consultarusuarios()
            if (!result.isNullOrEmpty()) {
                listempleadoModel.postValue(result!!)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }
    }


    suspend fun consultarusuarios():List<empleado>{

        return repository.getAllEmpleadosFromDatabase()

    }

     suspend operator fun invoke(empleadoinsert : empleado):List<empleado>{

        repository.insertempleado(empleadoinsert.toDataBase())

        return repository.getAllEmpleadosFromDatabase()

    }

}