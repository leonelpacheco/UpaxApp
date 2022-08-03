package com.leonel.upaxapp.ui.dataempleado

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.leonel.upaxapp.R
import com.leonel.upaxapp.adapters.EmpleadoAdapter
import com.leonel.upaxapp.databinding.FragmentEmpleadoBinding
import com.leonel.upaxapp.model.empleado
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class EmpleadoFragment : Fragment() {
    val REQUEST_IMAGE_CAPTURE = 1
    private var _binding: FragmentEmpleadoBinding? = null
    private var imageUri: Uri? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val empleadoViewModel =
            ViewModelProvider(this).get(EmpleadoViewModel::class.java)

        _binding = FragmentEmpleadoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rwlistempleados.layoutManager= LinearLayoutManager(activity)

        empleadoViewModel.consultarempleados()

        empleadoViewModel.listempleadoModel.observe(viewLifecycleOwner){
            val adapter = EmpleadoAdapter(it)
            binding.rwlistempleados.adapter= adapter

        }

        binding.imgImagenempleado.setOnClickListener(View.OnClickListener {
            val appPerms = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,

                )
            activityResultLauncher.launch(appPerms)
        })

        binding.btnGuardar.setOnClickListener(View.OnClickListener {
            if(  binding.txtNombre.text.isNullOrEmpty()
                || binding.txtTelefono.text.isNullOrEmpty()
                ||binding.txtEmail.text.isNullOrEmpty()
                ||binding.txtDireccion.text.isNullOrEmpty()
            )
                Toast.makeText(activity, "Faavor de llenar todos los campos", Toast.LENGTH_SHORT).show()
            else {
                val empleadosave = empleado(-1,binding.txtNombre.text.toString(),binding.txtTelefono.text.toString(),
                    binding.txtEmail.text.toString(),binding.txtDireccion.text.toString(),imageUri.toString())
                empleadoViewModel.onCreateEmpleado(empleadosave)

            }
        })

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//*************************
fun camera()
{

    val packageManager = requireActivity().packageManager
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            binding.imgImagenempleado.setImageURI(imageUri)
            binding.imgImagenempleado.setTag("send")
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            val bytes = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path: String = MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                imageBitmap,
                "imagecamera",
                null
            )
            imageUri = Uri.parse(path)


            binding.imgImagenempleado.setImageURI(imageUri)
            binding.imgImagenempleado.setTag("send")
        }
    }
    //Permisos
    private var activityResultLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { result ->
            var allAreGranted = true
            for(b in result.values) {
                allAreGranted = allAreGranted && b
            }

            if(allAreGranted) {
                camera()
            }
        }


    //***************

}