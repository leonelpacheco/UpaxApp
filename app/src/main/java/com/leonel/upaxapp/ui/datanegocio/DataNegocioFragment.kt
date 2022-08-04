package com.leonel.upaxapp.ui.datanegocio

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.leonel.upaxapp.R
import com.leonel.upaxapp.adapters.EmpleadoAdapter
import com.leonel.upaxapp.adapters.NegocioAdapter
import com.leonel.upaxapp.databinding.FragmentDataNegocioBinding
import com.leonel.upaxapp.databinding.FragmentEmpleadoBinding
import com.leonel.upaxapp.ui.dataempleado.EmpleadoViewModel
import com.leonel.upaxapp.ui.imagestore.ImageStoreFragment
import com.leonel.upaxapp.ui.imagestorelist.ImageStoreListFragment
import com.leonel.upaxapp.utils.ChangeFragment
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
        binding.rwlistnegocios.layoutManager= LinearLayoutManager(activity)
        datanegocioViewModel.onComercio()
        //datanegocioViewModel.insertarusuario()


        datanegocioViewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
        }

        datanegocioViewModel.listnegocioModel.observe(viewLifecycleOwner){
            val adapter = activity?.let { it1 -> it?.let { it2 -> NegocioAdapter(it2, requireActivity()) } }
            binding.rwlistnegocios.adapter= adapter
        }

        datanegocioViewModel.listcomercioModel.observe(viewLifecycleOwner){
            binding.txtNombre.setText(it.nombre)
            binding.txtEmail.setText(it.email)
            binding.txtTelefonocomercio.setText(it.telefono)
            binding.idcomercio.setText(it.fcIdComercio)
            Glide.with(requireActivity()).load(it.urlImagen).into(binding.imageView);
        }

        return root
    }
/*    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}