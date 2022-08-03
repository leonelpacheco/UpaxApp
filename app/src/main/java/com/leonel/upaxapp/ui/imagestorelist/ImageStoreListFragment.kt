package com.leonel.upaxapp.ui.imagestorelist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.leonel.upaxapp.adapters.imageAdapter
import com.leonel.upaxapp.databinding.FragmentImageStoreListBinding
import com.leonel.upaxapp.ui.imagestore.ImageStoreFragment
import com.leonel.upaxapp.utils.ChangeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageStoreListFragment : Fragment() {

    private var _binding: FragmentImageStoreListBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val imagestoreViewModelViewModel =
            ViewModelProvider(this).get(ImageStoreListViewModel::class.java)

        _binding = FragmentImageStoreListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rwlistimages.layoutManager= LinearLayoutManager(activity)

        imagestoreViewModelViewModel.obtenerimagenes()

        imagestoreViewModelViewModel.listimagesModel.observe(viewLifecycleOwner){
            val adapter = imageAdapter(it)
            binding.rwlistimages.adapter= adapter

        }

binding.fabAgregar.setOnClickListener(View.OnClickListener {
    val fragmentapp: Fragment =
        ImageStoreFragment()
    ChangeFragment.change(ImageStoreFragment(), getParentFragmentManager())
})
        return root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}