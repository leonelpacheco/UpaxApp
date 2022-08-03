package com.leonel.upaxapp.ui.datanegocio

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonel.upaxapp.R
import com.leonel.upaxapp.databinding.FragmentDataNegocioBinding
import com.leonel.upaxapp.databinding.FragmentEmpleadoBinding
import com.leonel.upaxapp.ui.dataempleado.EmpleadoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataNegocioFragment : Fragment() {

    private var _binding: FragmentDataNegocioBinding? = null
    private var imageUri: Uri? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val datanegocioViewModel =
            ViewModelProvider(this).get(DataNegocioViewModel::class.java)

        _binding = FragmentDataNegocioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        datanegocioViewModel.onCreate()

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}